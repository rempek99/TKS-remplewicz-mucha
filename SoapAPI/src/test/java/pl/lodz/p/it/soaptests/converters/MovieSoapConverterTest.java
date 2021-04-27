package pl.lodz.p.it.soaptests.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.lodz.p.it.soap.aggregates.converters.MovieSoapConverter;
import pl.lodz.p.it.soap.model.MovieSoap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovieSoapConverterTest {

    private final MovieSoap movieSoap = new MovieSoap("Test", "pl.lodz.p.it.viewports.movie", 7.9,false);
    private final Movie movie = new Movie("Test", "pl.lodz.p.it.viewports.movie", 7.9,false);

    @BeforeEach
    void init() {
        movieSoap.setId("212c5de0-ad07-444a-9100-fd422b4bff93");
        movie.setId("212c5de0-ad07-444a-9100-fd422b4bff93");
    }

    @Test
    void convertMovieToSoap() {
        MovieSoap check = MovieSoapConverter.convertMovieToMovieSoap(movie);
        assertEquals(check, movieSoap);
    }

    @Test
    void convertSoapToMovie() {
        Movie check = MovieSoapConverter.convertMovieSoapToMovie(movieSoap);
        assertEquals(check, movie);
    }
}