package pl.lodz.p.it.rentapplicationapi.rest.movie;

import pl.lodz.p.it.rentapplicationapi.aggregates.adapters.MovieServiceAdapter;
import pl.lodz.p.it.rentapplicationapi.rentviewmodel.modelDTO.MovieDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Optional;

@RequestScoped
@Path("movies")
public class MovieAPI {

    @Inject
    MovieServiceAdapter movieAdapter;

    // Queries

    @GET
    public Response getMoviesFromStorage() {
        return Response
                .ok()
                .entity(movieAdapter.getAllMovies())
                .status(Response.Status.OK)
                .build();
    }

    // Commands

    @POST
    public Response addSingleMovieToStorage(MovieDTO movie) {
        MovieDTO movieDTO = movieAdapter.addMovie(movie);
        return Response
                .ok()
//                .entity(movieDTO)
                .status(Response.Status.CREATED)
                .build();
    }

    @DELETE
    @Path("del/{str}")
    public Response removeSingleMovieFromStorage(@PathParam("str") String str) {
        Optional<MovieDTO> movie = Optional.ofNullable(movieAdapter.getMovieViaUUID(str));
        if(movie.isPresent()){
            movieAdapter.removeMovie(movie.get());
            return Response.ok().entity("Success").status(Response.Status.OK).build();
        }
        else{
            return Response.status(Response.Status.NO_CONTENT).entity("Wrong input data").build();
        }
    }

    @PUT
    @Path("update/{str}")
    public Response updateSingleMovie(@PathParam("str") String str, MovieDTO desiredMovie) {
        MovieDTO movieToChange = movieAdapter.getMovieViaUUID(str);
        movieAdapter.updateSingleMovie(movieToChange, desiredMovie);
        return Response.ok().entity("Movie updated succesfully!").status(Response.Status.OK).build();
    }

}
