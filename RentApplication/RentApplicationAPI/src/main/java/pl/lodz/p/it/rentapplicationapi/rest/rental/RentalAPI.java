package pl.lodz.p.it.rentapplicationapi.rest.rental;


import pl.lodz.p.it.rentapplicationapi.aggregates.adapters.RentalServiceAdapter;
import pl.lodz.p.it.rentapplicationapi.exceptions.RestException;
import pl.lodz.p.it.rentapplicationapi.rentviewmodel.modelDTO.BookRentalDTO;
import pl.lodz.p.it.rentapplicationapi.rentviewmodel.modelDTO.MovieRentalDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("rentals")
public class RentalAPI {

    @Inject
    RentalServiceAdapter rentalAdapter;

    // Queries

    @GET
    @Path("book")
    public Response getBooksRentalsFromStorage() {
        return Response.ok().entity(rentalAdapter.getAllBookRentals()).status(Response.Status.OK).build();
    }

    @GET
    @Path("movie")
    public Response getMoviesRentalsFromStorage() {
        return Response.ok().entity(rentalAdapter.getAllMovieRentals()).status(Response.Status.OK).build();
    }

    // Commands

    @POST
    @Path("book")
    public Response addSingleBookRentalToStorage(BookRentalDTO bookRental) {
        BookRentalDTO bookRentalDTO = rentalAdapter.addBookRental(bookRental);
        return Response
                .ok()
                //.entity(bookRentalDTO)
                .status(Response.Status.CREATED)
                .build();
    }

    @DELETE
    @Path("book/del/{str}")
    public Response removeSingleBookRentalFromStorage(@PathParam("str") String str) {
        try {
            BookRentalDTO bookRental = rentalAdapter.getBookRentalViaUUID(str);
                rentalAdapter.removeBookRental(bookRental);
                return Response.ok().entity("Success").status(Response.Status.OK).build();
        }catch (RestException e) {
            return Response.status(Response.Status.NO_CONTENT).entity("Wrong input data").build();
        }
    }

    @PUT
    @Path("book/update/{str}")
    public Response updateSingleBookRentalFromStorage(@PathParam("str") String str, BookRentalDTO desiredBookRental) {
        try {
            BookRentalDTO bookRentalToChange = rentalAdapter.getBookRentalViaUUID(str);
            rentalAdapter.updateSingleBookRental(bookRentalToChange, desiredBookRental);
            return Response.ok().entity("Book rental updated succesfully!").status(Response.Status.OK).build();
        }catch (RestException e) {
            return Response.status(Response.Status.NO_CONTENT).entity("Wrong input data").build();
        }
    }

    @POST
    @Path("movie")
    public Response addSingleMovieRentalToStorage(MovieRentalDTO movieRental) {
        MovieRentalDTO movieRentalDTO = rentalAdapter.addMovieRental(movieRental);
        return Response
                .ok()
//                .entity(movieRentalDTO)
                .status(Response.Status.CREATED)
                .build();
    }

    @DELETE
    @Path("movie/del/{str}")
    public Response removeSingleMovieRentalFromStorage(@PathParam("str") String str) {
        try {
            MovieRentalDTO movieRental = rentalAdapter.getMovieRentalViaUUID(str);
            rentalAdapter.removeMovieRental(movieRental);
                return Response.ok().entity("Success").status(Response.Status.OK).build();
        }
        catch(RestException e){
            return Response.status(Response.Status.NO_CONTENT).entity("Wrong input data").build();
        }
    }

    @PUT
    @Path("movie/update/{str}")
    public Response updateSingleMovieRentalFromStorage(@PathParam("str") String str, MovieRentalDTO desiredMovieRental) {
        try {
            MovieRentalDTO movieRentalToChange = rentalAdapter.getMovieRentalViaUUID(str);
            rentalAdapter.updateSingleMovieRental(movieRentalToChange, desiredMovieRental);
            return Response.ok().entity("Movie rental updated succesfully!").status(Response.Status.OK).build();
        }catch(RestException e){
            return Response.status(Response.Status.NO_CONTENT).entity("Wrong input data").build();
        }
    }
}
