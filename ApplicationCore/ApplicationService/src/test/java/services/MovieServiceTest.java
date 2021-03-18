package services;

import model.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieServiceTest {

    private MovieService movieService;

    private final Movie tester = new Movie("Test", "Tester", 100,false);
    private final Movie tester2 = new Movie("Test2", "Tester2", 200, true);
    private final Movie tester3 = new Movie("Test3", "Tester3", 300, true);

    @BeforeEach
    void initMovieService() {
       // movieService = new MovieService(new MovieRepoAdapter(new MovieEntRepo()));
        movieService.addMovie(tester);
    }

    @Test
    void getAllMovies() {
        List<Movie> movieList = movieService.getAllMovies();
        assertFalse(movieList.isEmpty());
    }

    @Test
    void addMovie() {
        int size = movieService.getAllMovies().size();
        movieService.addMovie(tester2);
        assertEquals(size+1, movieService.getAllMovies().size());
    }

    @Test
    void setMovieRented() {
        Movie exampleMovie = movieService.getAllMovies().get(0);
        assertFalse(exampleMovie.isRented());
        movieService.setMovieRented(exampleMovie,true);
        assertTrue(movieService.getMovieViaUUID(exampleMovie.getId()).isRented());
    }

    @Test
    void removeMovie() {
        int size = movieService.getAllMovies().size();
        Movie exampleMovie = movieService.getAllMovies().get(0);
        movieService.removeMovie(exampleMovie);
        assertEquals(size-1, movieService.getAllMovies().size());
    }

    @Test
    void getMovieViaUUID() {
        Movie exampleMovie = movieService.getAllMovies().get(0);
        Movie exampleMovieByID = movieService.getMovieViaUUID(exampleMovie.getId());
        assertEquals(exampleMovie,exampleMovieByID);
    }

    @Test
    void updateSingleMovie() {
        Movie exampleMovie = movieService.getAllMovies().get(0);
        movieService.updateSingleMovie(exampleMovie, tester2);
        Movie foundMovie = movieService.getMovieViaUUID(exampleMovie.getId());
        assertEquals(tester2.getTitle(), foundMovie.getTitle());
    }
}