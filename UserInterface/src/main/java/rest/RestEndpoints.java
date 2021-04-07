package rest;


import account.*;
import book.*;
import movie.*;
import rentals.*;
import modelDTO.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.Optional;

@RequestScoped
@Path("/")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class RestEndpoints implements Serializable {

    @Inject
    private GetAllAccountsUsecase getAllAccountsService;
    @Inject
    private GetAccountViaUUIDUsecase getAccountViaUUIDService;
    @Inject
    private AddAccountUsecase addAccountService;
    @Inject
    private RemoveAccountUsecase removeAccountService;
    @Inject
    private UpdateSingleAccountUsecase updateAccountService;
    @Inject
    private GetAllMoviesUsecase getAllMoviesService;
    @Inject
    private GetMovieViaUUIDUsecase getMovieViaUUIDService;
    @Inject
    private AddMovieUsecase addMovieService;
    @Inject
    private RemoveMovieUsecase removeMovieService;
    @Inject
    private UpdateSingleMovieUsecase updateMovieService;
    @Inject
    private GetAllBooksUsecase getAllBooksService;
    @Inject
    private GetBookViaUUIDUsecase getBookViaUUIDService;
    @Inject
    private AddBookUsecase addBookService;
    @Inject
    private RemoveBookUsecase removeBookService;
    @Inject
    private UpdateSingleBookUsecase updateBookService;
    @Inject
    private GetAllBookRentalsUsecase getAllBookRentalsService;
    @Inject
    private GetBookRentalViaUUIDUsecase getBookRentalViaUUIDService;
    @Inject
    private AddBookRentalUsecase addBookRentalService;
    @Inject
    private RemoveBookRentalUsecase removeBookRentalService;
    @Inject
    private UpdateSingleBookRentalUsecase updateBookRentalService;
    @Inject
    private GetAllMovieRentalsUsecase getAllMovieRentalsService;
    @Inject
    private GetMovieRentalViaUUIDUsecase getMovieRentalViaUUIDService;
    @Inject
    private AddMovieRentalUsecase addMovieRentalService;
    @Inject
    private RemoveMovieRentalUsecase removeMovieRentalService;
    @Inject
    private UpdateSingleMovieRentalUsecase updateMovieRentalService;

    // accounts section

    @GET
    @Path("accounts")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccFromStorage() {
        return Response.ok().entity(getAllAccountsService.getAllAccounts()).status(Response.Status.OK).build();
    }

    @GET
    @Path("accounts/{str}")
    public Response getSingleAccFromStorage(@PathParam("str") String str) {
        return Response.ok().entity(getAccountViaUUIDService.getAccountViaUUID(str)).status(Response.Status.OK).build();
    }

    @POST
    @Path("accounts")
    public Response addSingleAccToStorage(@Valid AccountDTO account) {
        addAccountService.addAccount(account);
        return Response.ok().entity("Success").status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("accounts/del/{str}")
    public Response removeSingleAccFromStorage(@PathParam("str") String str) {
        Optional<AccountDTO> acc = Optional.ofNullable(getAccountViaUUIDService.getAccountViaUUID(str));
        if(acc.isPresent()){
            removeAccountService.removeAccount(acc.get());
            return Response.ok().entity("Success").status(Response.Status.OK).build();
        }
        else{
            return Response.status(Response.Status.NO_CONTENT).entity("Wrong input data").build();
        }
    }

    @PUT
    @Path("accounts/update/{str}")
    public Response updateSingleAccount(@PathParam("str") String str, @Valid AccountDTO desiredAccount) {
        AccountDTO accountToChange = getAccountViaUUIDService.getAccountViaUUID(str);
        updateAccountService.updateSingleAccount(accountToChange, desiredAccount);
        return Response.ok().entity("Account updated succesfully!").status(Response.Status.OK).build();
    }

    // movies section

    @GET
    @Path("movies")
    public Response getMoviesFromStorage() {
        return Response.ok().entity(getAllMoviesService.getAllMovies()).status(Response.Status.OK).build();
    }

    @POST
    @Path("movies")
    public Response addSingleMovieToStorage(MovieDTO movie) {
        addMovieService.addMovie(movie);
        return Response.ok().entity("Success").status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("movies/del/{str}")
    public Response removeSingleMovieFromStorage(@PathParam("str") String str) {
        Optional<MovieDTO> movie = Optional.ofNullable(getMovieViaUUIDService.getMovieViaUUID(str));
        if(movie.isPresent()){
            removeMovieService.removeMovie(movie.get());
            return Response.ok().entity("Success").status(Response.Status.OK).build();
        }
        else{
            return Response.status(Response.Status.NO_CONTENT).entity("Wrong input data").build();
        }
    }

    @PUT
    @Path("movies/update/{str}")
    public Response updateSingleMovie(@PathParam("str") String str, MovieDTO desiredMovie) {
        MovieDTO movieToChange = getMovieViaUUIDService.getMovieViaUUID(str);
        updateMovieService.updateSingleMovie(movieToChange, desiredMovie);
        return Response.ok().entity("Movie updated succesfully!").status(Response.Status.OK).build();
    }

    // books section

    @GET
    @Path("books")
    public Response getBooksFromStorage() {
        return Response.ok().entity(getAllBooksService.getAllBooks()).status(Response.Status.OK).build();
    }

    @POST
    @Path("books")
    public Response addSingleBookToStorage(BookDTO book) {
        addBookService.addBook(book);
        return Response.ok().entity("Success").status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("books/del/{str}")
    public Response removeSingleBookFromStorage(@PathParam("str") String str) {
        Optional<BookDTO> book = Optional.ofNullable(getBookViaUUIDService.getBookViaUUID(str));
        if(book.isPresent()){
            removeBookService.removeBook(book.get());
            return Response.ok().entity("Success").status(Response.Status.OK).build();
        }
        else{
            return Response.status(Response.Status.NO_CONTENT).entity("Wrong input data").build();
        }
    }

    @PUT
    @Path("books/update/{str}")
    public Response updateSingleBook(@PathParam("str") String str, BookDTO desiredBook) {
        BookDTO bookToChange = getBookViaUUIDService.getBookViaUUID(str);
        updateBookService.updateSingleBook(bookToChange, desiredBook);
        return Response.ok().entity("Book updated succesfully!").status(Response.Status.OK).build();
    }

    // rentals section

    @GET
    @Path("bookRentals")
    public Response getBooksRentalsFromStorage() {
        return Response.ok().entity(getAllBookRentalsService.getAllBookRentals()).status(Response.Status.OK).build();
    }

    @POST
    @Path("bookRentals")
    public Response addSingleBookRentalToStorage(BookRentalDTO bookRental) {
        addBookRentalService.addBookRental(bookRental);
        return Response.ok().entity("Success").status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("bookRentals/del/{str}")
    public Response removeSingleBookRentalFromStorage(@PathParam("str") String str) {
        Optional<BookRentalDTO> bookRental = Optional.ofNullable(getBookRentalViaUUIDService.getBookRentalViaUUID(str));
        if(bookRental.isPresent()){
            removeBookRentalService.removeBookRental(bookRental.get());
            return Response.ok().entity("Success").status(Response.Status.OK).build();
        }
        else{
            return Response.status(Response.Status.NO_CONTENT).entity("Wrong input data").build();
        }
    }

    @PUT
    @Path("bookRentals/update/{str}")
    public Response updateSingleBookRentalFromStorage(@PathParam("str") String str, BookRentalDTO desiredBookRental) {
        BookRentalDTO bookRentalToChange = getBookRentalViaUUIDService.getBookRentalViaUUID(str);
        updateBookRentalService.updateSingleBookRental(bookRentalToChange, desiredBookRental);
        return Response.ok().entity("Book rental updated succesfully!").status(Response.Status.OK).build();
    }

    @GET
    @Path("movieRentals")
    public Response getMoviesRentalsFromStorage() {
        return Response.ok().entity(getAllMovieRentalsService.getAllMovieRentals()).status(Response.Status.OK).build();
    }

    @POST
    @Path("movieRentals")
    public Response addSingleMovieRentalToStorage(MovieRentalDTO movieRental) {
        addMovieRentalService.addMovieRental(movieRental);
        return Response.ok().entity("Success").status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("movieRentals/del/{str}")
    public Response removeSingleMovieRentalFromStorage(@PathParam("str") String str) {
        Optional<MovieRentalDTO> movieRental = Optional.ofNullable(getMovieRentalViaUUIDService.getMovieRentalViaUUID(str));
        if(movieRental.isPresent()){
            removeMovieRentalService.removeMovieRental(movieRental.get());
            return Response.ok().entity("Success").status(Response.Status.OK).build();
        }
        else{
            return Response.status(Response.Status.NO_CONTENT).entity("Wrong input data").build();
        }
    }

    @PUT
    @Path("movieRentals/update/{str}")
    public Response updateSingleMovieRentalFromStorage(@PathParam("str") String str, MovieRentalDTO desiredMovieRental) {
        MovieRentalDTO movieRentalToChange = getMovieRentalViaUUIDService.getMovieRentalViaUUID(str);
        updateMovieRentalService.updateSingleMovieRental(movieRentalToChange, desiredMovieRental);
        return Response.ok().entity("Movie rental updated succesfully!").status(Response.Status.OK).build();
    }
}
