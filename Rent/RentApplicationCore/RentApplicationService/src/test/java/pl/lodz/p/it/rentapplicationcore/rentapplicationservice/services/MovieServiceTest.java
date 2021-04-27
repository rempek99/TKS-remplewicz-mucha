package pl.lodz.p.it.rentapplicationcore.rentapplicationservice.services;

import pl.lodz.p.it.applicationports.infrastructure.MoviePort;
import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Movie;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @InjectMocks
    private MovieService movieService;

    @Mock(name = "moviePort")
    private MoviePort moviePort;

    private final Movie tester = new Movie("Test", "Tester", 100,false);
    private final Movie tester2 = new Movie("Test2", "Tester2", 200, true);
    private final Movie tester3 = new Movie("Test3", "Tester3", 300, true);
    private final List<Movie> movieList = new ArrayList<>(List.of(tester,tester2,tester3));

    @Test
    void getAllMovies() {
        //given
        given(moviePort.getAllMovies()).willReturn(movieList);
        //when
        List<Movie> movieList = movieService.getAll();
        //then
        then(moviePort).should().getAllMovies();
        then(moviePort).shouldHaveNoMoreInteractions();
        assertFalse(movieList.isEmpty());
    }

    @Test
    void addMovie() {
        //given
            //tester2
        //when
        movieService.add(tester2);
        //then
        then(moviePort).should().addMovie(tester2);
        then(moviePort).shouldHaveNoMoreInteractions();
    }

    @Test
    void setMovieRented() {
        //given
        Movie exampleMovie = tester;
        assertFalse(exampleMovie.isRented());
        //when
        movieService.setMovieRented(exampleMovie,true);
        //then
        then(moviePort).should().setMovieRented(exampleMovie,true);
        then(moviePort).shouldHaveNoMoreInteractions();
    }

    @Test
    void removeMovie() {
        //given
        Movie exampleMovie = tester;
        //when
        movieService.remove(exampleMovie);
        //then
        then(moviePort).should().removeMovie(exampleMovie);
        then(moviePort).shouldHaveNoMoreInteractions();
    }

    @Test
    void getMovieViaUUID() {
        //given
        Movie exampleMovie = tester;
        given(moviePort.getMovieViaUUID(exampleMovie.getId())).willReturn(Optional.of(exampleMovie));
        //when
        Movie exampleMovieByID = movieService.getViaUUID(exampleMovie.getId()).get();
        //then
        then(moviePort).should().getMovieViaUUID(exampleMovie.getId());
        then(moviePort).shouldHaveNoMoreInteractions();
        assertEquals(exampleMovie,exampleMovieByID);
    }

    @Test
    void updateSingleMovie() {
        //given
        Movie exampleMovie = tester;
        //when
        movieService.update(exampleMovie, tester2);
        //then
        then(moviePort).should().updateSingleMovie(exampleMovie,tester2);
        then(moviePort).shouldHaveNoMoreInteractions();
    }
}