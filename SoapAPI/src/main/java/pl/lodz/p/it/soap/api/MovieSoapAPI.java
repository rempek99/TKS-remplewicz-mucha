package pl.lodz.p.it.soap.api;


import pl.lodz.p.it.repositoriesadapters.model_ent.repositories.RepositoryException;
import pl.lodz.p.it.soap.aggregates.adapters.MovieSoapAdapter;
import pl.lodz.p.it.soap.model.BookSoap;
import pl.lodz.p.it.soap.model.MovieSoap;
import pl.lodz.p.it.soap.model.SoapException;
import pl.lodz.p.it.soap.model.SoapMessage;

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


    public MovieSoap addSingleMovieToStorage(MovieSoap movie) throws SoapException {
        try {
            return movieAdapter.addMovie(movie);
        } catch (IllegalArgumentException e) {
            if (e.getCause().getMessage().equals(RepositoryException.DUPLICATED))
                throw new SoapException(SoapException.DUPLICATED);
            else
                throw e;
        }
    }

    public MovieSoap getSingleMovieFromStorage(String str) throws SoapException {
        try {
            return movieAdapter.getMovieViaUUID(str);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals(SoapException.NOT_FOUND))
                throw new SoapException(SoapException.NOT_FOUND);
            else
                throw e;
        }
    }

    public String removeSingleMovieFromStorage(String str) throws SoapException {
        Optional<MovieSoap> movie = Optional.ofNullable(movieAdapter.getMovieViaUUID(str));
        if (movie.isPresent()) {
            movieAdapter.removeMovie(movie.get());
            return SoapMessage.OK;
        } else {
            throw new SoapException(SoapException.NOT_FOUND);
        }
    }

    public String updateSingleMovie(String str, MovieSoap desiredMovie) throws SoapException {
        Optional<MovieSoap> movieTOChange = Optional.ofNullable(movieAdapter.getMovieViaUUID(str));
        if (movieTOChange.isPresent()) {
            movieAdapter.updateSingleMovie(movieTOChange.get(), desiredMovie);
            return SoapMessage.OK;
        } else {
            throw new SoapException(SoapException.NOT_FOUND);
        }
    }
}
