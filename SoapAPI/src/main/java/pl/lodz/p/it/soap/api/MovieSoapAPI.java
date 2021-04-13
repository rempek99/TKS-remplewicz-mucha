package pl.lodz.p.it.soap.api;


import pl.lodz.p.it.soap.aggregates.adapters.MovieSoapAdapter;
import pl.lodz.p.it.soap.model.MovieSoap;

import javax.inject.Inject;
import javax.jws.WebService;
import java.util.List;
import java.util.Optional;

@WebService(serviceName = "MovieAPI")
public class MovieSoapAPI {

    @Inject
    MovieSoapAdapter movieAdapter;


    public List<MovieSoap> getMoviesFromStorage() {
        return movieAdapter.getAllMovies();
    }


    public void addSingleMovieToStorage(MovieSoap movie) {
        movieAdapter.addMovie(movie);
    }


    public void removeSingleMovieFromStorage(String str) {
        Optional<MovieSoap> movie = Optional.ofNullable(movieAdapter.getMovieViaUUID(str));
        if (movie.isPresent()) {
            movieAdapter.removeMovie(movie.get());
        } else {
            throw new IllegalArgumentException("Movie not found");
        }
    }

    public void updateSingleMovie(String str, MovieSoap desiredMovie) {
        Optional<MovieSoap> movieTOChange = Optional.ofNullable(movieAdapter.getMovieViaUUID(str));
        if (movieTOChange.isPresent()) {
            movieAdapter.updateSingleMovie(movieTOChange.get(), desiredMovie);
        } else {
            throw new IllegalArgumentException("Movie not found");
        }
    }
}
