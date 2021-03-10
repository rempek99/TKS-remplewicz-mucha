package rest;


import controller.AccountService;
import controller.BookService;
import controller.MovieService;
import controller.RentalService;
import model.*;

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
    private AccountService accountService;
    @Inject
    private MovieService movieService;
    @Inject
    private BookService bookService;
    @Inject
    private RentalService rentalService;

    // accounts section

    @GET
    @Path("accounts")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccFromStorage() {
        return Response.ok().entity(accountService.getAllAccounts()).status(Response.Status.OK).build();
    }

    @GET
    @Path("accounts/{str}")
    public Response getSingleAccFromStorage(@PathParam("str") String str) {
        return Response.ok().entity(accountService.getAccountViaUUID(str)).status(Response.Status.OK).build();
    }

    @POST
    @Path("accounts")
    public Response addSingleAccToStorage(@Valid Account account) {
        accountService.addAccount(account);
        return Response.ok().entity("Success").status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("accounts/del/{str}")
    public Response removeSingleAccFromStorage(@PathParam("str") String str) {
        Optional<Account> acc = Optional.ofNullable(accountService.getAccountViaUUID(str));
        if(acc.isPresent()){
            accountService.removeAccount(acc.get());
            return Response.ok().entity("Success").status(Response.Status.OK).build();
        }
        else{
            return Response.status(Response.Status.NO_CONTENT).entity("Wrong input data").build();
        }
    }

    @PUT
    @Path("accounts/update/{str}")
    public Response updateSingleAccount(@PathParam("str") String str, @Valid Account desiredAccount) {
        Account accountToChange = accountService.getAccountViaUUID(str);
        accountService.updateSingleAccount(accountToChange, desiredAccount);
        return Response.ok().entity("Account updated succesfully!").status(Response.Status.OK).build();
    }

    // movies section

    @GET
    @Path("movies")
    public Response getMoviesFromStorage() {
        return Response.ok().entity(movieService.getAllMovies()).status(Response.Status.OK).build();
    }

    @POST
    @Path("movies")
    public Response addSingleMovieToStorage(Movie movie) {
        movieService.addMovie(movie);
        return Response.ok().entity("Success").status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("movies/del/{str}")
    public Response removeSingleMovieFromStorage(@PathParam("str") String str) {
        Optional<Movie> movie = Optional.ofNullable(movieService.getMovieViaUUID(str));
        if(movie.isPresent()){
            movieService.removeMovie(movie.get());
            return Response.ok().entity("Success").status(Response.Status.OK).build();
        }
        else{
            return Response.status(Response.Status.NO_CONTENT).entity("Wrong input data").build();
        }
    }

    @PUT
    @Path("movies/update/{str}")
    public Response updateSingleMovie(@PathParam("str") String str, Movie desiredMovie) {
        Movie movieToChange = movieService.getMovieViaUUID(str);
        movieService.updateSingleMovie(movieToChange, desiredMovie);
        return Response.ok().entity("Movie updated succesfully!").status(Response.Status.OK).build();
    }

    // books section

    @GET
    @Path("books")
    public Response getBooksFromStorage() {
        return Response.ok().entity(bookService.getAllBooks()).status(Response.Status.OK).build();
    }

    @POST
    @Path("books")
    public Response addSingleBookToStorage(Book book) {
        bookService.addBook(book);
        return Response.ok().entity("Success").status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("books/del/{str}")
    public Response removeSingleBookFromStorage(@PathParam("str") String str) {
        Optional<Book> book = Optional.ofNullable(bookService.getBookViaUUID(str));
        if(book.isPresent()){
            bookService.removeBook(book.get());
            return Response.ok().entity("Success").status(Response.Status.OK).build();
        }
        else{
            return Response.status(Response.Status.NO_CONTENT).entity("Wrong input data").build();
        }
    }

    @PUT
    @Path("books/update/{str}")
    public Response updateSingleBook(@PathParam("str") String str, Book desiredBook) {
        Book bookToChange = bookService.getBookViaUUID(str);
        bookService.updateSingleBook(bookToChange, desiredBook);
        return Response.ok().entity("Book updated succesfully!").status(Response.Status.OK).build();
    }

    // rentals section

    @GET
    @Path("bookRentals")
    public Response getBooksRentalsFromStorage() {
        return Response.ok().entity(rentalService.getAllBookRentals()).status(Response.Status.OK).build();
    }

    @POST
    @Path("bookRentals")
    public Response addSingleBookRentalToStorage(BookRental bookRental) {
        rentalService.addBookRental(bookRental);
        return Response.ok().entity("Success").status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("bookRentals/del/{str}")
    public Response removeSingleBookRentalFromStorage(@PathParam("str") String str) {
        Optional<BookRental> bookRental = Optional.ofNullable(rentalService.getBookRentalViaUUID(str));
        if(bookRental.isPresent()){
            rentalService.removeBookRental(bookRental.get());
            return Response.ok().entity("Success").status(Response.Status.OK).build();
        }
        else{
            return Response.status(Response.Status.NO_CONTENT).entity("Wrong input data").build();
        }
    }

    @PUT
    @Path("bookRentals/update/{str}")
    public Response updateSingleBookRentalFromStorage(@PathParam("str") String str, BookRental desiredBookRental) {
        BookRental bookRentalToChange = rentalService.getBookRentalViaUUID(str);
        rentalService.updateSingleBookRental(bookRentalToChange, desiredBookRental);
        return Response.ok().entity("Book rental updated succesfully!").status(Response.Status.OK).build();
    }

    @GET
    @Path("movieRentals")
    public Response getMoviesRentalsFromStorage() {
        return Response.ok().entity(rentalService.getAllMovieRentals()).status(Response.Status.OK).build();
    }

    @POST
    @Path("movieRentals")
    public Response addSingleMovieRentalToStorage(MovieRental movieRental) {
        rentalService.addMovieRental(movieRental);
        return Response.ok().entity("Success").status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("movieRentals/del/{str}")
    public Response removeSingleMovieRentalFromStorage(@PathParam("str") String str) {
        Optional<MovieRental> movieRental = Optional.ofNullable(rentalService.getMovieRentalViaUUID(str));
        if(movieRental.isPresent()){
            rentalService.removeMovieRental(movieRental.get());
            return Response.ok().entity("Success").status(Response.Status.OK).build();
        }
        else{
            return Response.status(Response.Status.NO_CONTENT).entity("Wrong input data").build();
        }
    }

    @PUT
    @Path("movieRentals/update/{str}")
    public Response updateSingleMovieRentalFromStorage(@PathParam("str") String str, MovieRental desiredMovieRental) {
        MovieRental movieRentalToChange = rentalService.getMovieRentalViaUUID(str);
        rentalService.updateSingleMovieRental(movieRentalToChange, desiredMovieRental);
        return Response.ok().entity("Movie rental updated succesfully!").status(Response.Status.OK).build();
    }
}