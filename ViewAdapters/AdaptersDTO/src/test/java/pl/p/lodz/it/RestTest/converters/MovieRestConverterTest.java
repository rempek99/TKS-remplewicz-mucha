package pl.p.lodz.it.RestTest.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.lodz.p.it.applicationcore.domainmodel.model.Movie;
import pl.lodz.p.it.viewadapters.converters.MovieConverter;
import pl.lodz.p.it.viewmodel.modelDTO.MovieDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovieRestConverterTest {

    private final MovieDTO movieDTO = new MovieDTO("Test", "pl.lodz.p.it.viewports.movie", 7.9,false);
    private final Movie movie = new Movie("Test", "pl.lodz.p.it.viewports.movie", 7.9,false);

    @BeforeEach
    void init() {
        movieDTO.setId("212c5de0-ad07-444a-9100-fd422b4bff93");
        movie.setId("212c5de0-ad07-444a-9100-fd422b4bff93");
    }

    @Test
    void convertMovieToDTO() {
        MovieDTO check = MovieConverter.convertMovieToDTO(movie);
        assertEquals(check, movieDTO);
    }

    @Test
    void convertDTOToMovie() {
        Movie check = MovieConverter.convertDTOToMovie(movieDTO);
        assertEquals(check, movie);
    }
}