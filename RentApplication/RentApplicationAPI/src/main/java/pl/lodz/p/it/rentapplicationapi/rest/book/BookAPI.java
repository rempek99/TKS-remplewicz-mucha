package pl.lodz.p.it.rentapplicationapi.rest.book;

import pl.lodz.p.it.rentapplicationapi.aggregates.adapters.BookServiceAdapter;
import pl.lodz.p.it.rentapplicationapi.rentviewmodel.modelDTO.BookDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Optional;

@RequestScoped
@Path("books")
public class BookAPI {

    @Inject
    BookServiceAdapter bookAdapter;

    // Queries

    @GET
    public Response getBooksFromStorage() {
        return Response.ok().entity(bookAdapter.getAllBooks()).status(Response.Status.OK).build();
    }

    // Commands

    @POST
    public Response addSingleBookToStorage(BookDTO book) {
        BookDTO bookDTO = bookAdapter.addBook(book);
        return Response
                .ok()
                //.entity(bookDTO)
                .status(Response.Status.CREATED)
                .build();
    }

    @DELETE
    @Path("del/{str}")
    public Response removeSingleBookFromStorage(@PathParam("str") String str) {
        Optional<BookDTO> book = Optional.ofNullable(bookAdapter.getBookViaUUID(str));
        if(book.isPresent()){
            bookAdapter.removeBook(book.get());
            return Response.ok().entity("Success").status(Response.Status.OK).build();
        }
        else{
            return Response.status(Response.Status.NO_CONTENT).entity("Wrong input data").build();
        }
    }

    @PUT
    @Path("update/{str}")
    public Response updateSingleBook(@PathParam("str") String str, BookDTO desiredBook) {
        BookDTO bookToChange = bookAdapter.getBookViaUUID(str);
        bookAdapter.updateSingleBook(bookToChange, desiredBook);
        return Response.ok().entity("Book updated succesfully!").status(Response.Status.OK).build();
    }

}
