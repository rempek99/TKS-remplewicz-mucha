package converters;

import aggregates.converters.MovieConverter;
import model.Movie;
import model_ent.entities.MovieEnt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovieConverterTest {

    private final MovieEnt movieEnt = new MovieEnt("Test", "movie", 7.9,false);
    private final Movie movie = new Movie("Test", "movie", 7.9,false);

    @BeforeEach
    void init() {
        movieEnt.setId("212c5de0-ad07-444a-9100-fd422b4bff93");
        movie.setId("212c5de0-ad07-444a-9100-fd422b4bff93");
    }

    @Test
    void convertMovieToEnt() {
        MovieEnt check = MovieConverter.convertMovieToEnt(movie);
        assertEquals(check, movieEnt);
    }

    @Test
    void convertEntToMovie() {
        Movie check = MovieConverter.convertEntToMovie(movieEnt);
        assertEquals(check, movie);
    }
}