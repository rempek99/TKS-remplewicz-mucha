package pl.lodz.p.it.userinterface.rest.rental;


import pl.lodz.p.it.viewadapters.adapters.RentalServiceAdapter;
import pl.lodz.p.it.viewmodel.modelDTO.BookRentalDTO;
import pl.lodz.p.it.viewmodel.modelDTO.MovieRentalDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Optional;

@RequestScoped
@Path("retnals")
public class RentalAPI {

    @Inject
    RentalServiceAdapter rentalAdapter;

    @GET
    @Path("book")
    public Response getBooksRentalsFromStorage() {
        return Response.ok().entity(rentalAdapter.getAllBookRentals()).status(Response.Status.OK).build();
    }

    @POST
    @Path("book")
    public Response addSingleBookRentalToStorage(BookRentalDTO bookRental) {
        rentalAdapter.addBookRental(bookRental);
        return Response.ok().entity("Success").status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("book/del/{str}")
    public Response removeSingleBookRentalFromStorage(@PathParam("str") String str) {
        Optional<BookRentalDTO> bookRental = Optional.ofNullable(rentalAdapter.getBookRentalViaUUID(str));
        if(bookRental.isPresent()){
            rentalAdapter.removeBookRental(bookRental.get());
            return Response.ok().entity("Success").status(Response.Status.OK).build();
        }
        else{
            return Response.status(Response.Status.NO_CONTENT).entity("Wrong input data").build();
        }
    }

    @PUT
    @Path("book/update/{str}")
    public Response updateSingleBookRentalFromStorage(@PathParam("str") String str, BookRentalDTO desiredBookRental) {
        BookRentalDTO bookRentalToChange = rentalAdapter.getBookRentalViaUUID(str);
        rentalAdapter.updateSingleBookRental(bookRentalToChange, desiredBookRental);
        return Response.ok().entity("Book rental updated succesfully!").status(Response.Status.OK).build();
    }

    @GET
    @Path("movie")
    public Response getMoviesRentalsFromStorage() {
        return Response.ok().entity(rentalAdapter.getAllMovieRentals()).status(Response.Status.OK).build();
    }

    @POST
    @Path("movie")
    public Response addSingleMovieRentalToStorage(MovieRentalDTO movieRental) {
        rentalAdapter.addMovieRental(movieRental);
        return Response.ok().entity("Success").status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("movie/del/{str}")
    public Response removeSingleMovieRentalFromStorage(@PathParam("str") String str) {
        Optional<MovieRentalDTO> movieRental = Optional.ofNullable(rentalAdapter.getMovieRentalViaUUID(str));
        if(movieRental.isPresent()){
            rentalAdapter.removeMovieRental(movieRental.get());
            return Response.ok().entity("Success").status(Response.Status.OK).build();
        }
        else{
            return Response.status(Response.Status.NO_CONTENT).entity("Wrong input data").build();
        }
    }

    @PUT
    @Path("movie/update/{str}")
    public Response updateSingleMovieRentalFromStorage(@PathParam("str") String str, MovieRentalDTO desiredMovieRental) {
        MovieRentalDTO movieRentalToChange = rentalAdapter.getMovieRentalViaUUID(str);
        rentalAdapter.updateSingleMovieRental(movieRentalToChange, desiredMovieRental);
        return Response.ok().entity("Movie rental updated succesfully!").status(Response.Status.OK).build();
    }
}
