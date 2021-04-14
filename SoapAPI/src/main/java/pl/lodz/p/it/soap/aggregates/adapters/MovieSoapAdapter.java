package pl.lodz.p.it.soap.aggregates.adapters;

import pl.lodz.p.it.applicationcore.applicationservice.services.MovieService;
import pl.lodz.p.it.applicationports.usecase.movie.MovieUsecaseSuit;
import pl.lodz.p.it.soap.aggregates.converters.MovieSoapConverter;
import pl.lodz.p.it.soap.model.SoapException;
import pl.lodz.p.it.soap.model.MovieSoap;
import pl.lodz.p.it.viewports.movie.MovieViewPortUsecaseSuit;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Dependent
public class MovieSoapAdapter implements MovieViewPortUsecaseSuit<MovieSoap>, Serializable {
    private MovieUsecaseSuit movieService;

    @Inject
    public MovieSoapAdapter(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public List<MovieSoap> getAllMovies() {
        return movieService
                .getAll()
                .stream()
                .map(MovieSoapConverter::convertMovieToMovieSoap)
                .collect(Collectors.toList());
    }

    @Override
    public MovieSoap addMovie(MovieSoap m) {
        return MovieSoapConverter.convertMovieToMovieSoap(
                movieService.add(MovieSoapConverter.convertMovieSoapToMovie(m))
        );
    }

    @Override
    public void setMovieRented(MovieSoap m, boolean value) {
        movieService.setMovieRented(MovieSoapConverter.convertMovieSoapToMovie(m), value);
    }

    @Override
    public void removeMovie(MovieSoap m) {
        movieService.remove(MovieSoapConverter.convertMovieSoapToMovie(m));
    }

    @Override
    public MovieSoap getMovieViaUUID(String str) {
        if(movieService.getViaUUID(str).isPresent()) {
            return MovieSoapConverter.convertMovieToMovieSoap(
                    movieService.getViaUUID(str).get());
        }
        else
            throw new IllegalArgumentException(SoapException.NOT_FOUND);
    }

    @Override
    public MovieSoap getMovie(MovieSoap m) {
        return MovieSoapConverter.convertMovieToMovieSoap(movieService.get(MovieSoapConverter.convertMovieSoapToMovie(m)));
    }

    @Override
    public void updateSingleMovie(MovieSoap movieToChange, MovieSoap movieWithData) {
        movieService.update(
                MovieSoapConverter.convertMovieSoapToMovie(movieToChange),
                MovieSoapConverter.convertMovieSoapToMovie(movieWithData)
        );
    }

}
