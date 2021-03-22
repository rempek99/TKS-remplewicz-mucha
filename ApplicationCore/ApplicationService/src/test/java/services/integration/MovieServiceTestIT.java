package services.integration;

import aggregates.adapters.MovieRepoAdapter;
import model.Movie;
import model_ent.repositories.MovieEntRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.MovieService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieServiceTestIT {

    private MovieService movieService;

    private final Movie tester = new Movie("Test", "Tester", 100,false);
    private final Movie tester2 = new Movie("Test2", "Tester2", 200, true);
    private final Movie tester3 = new Movie("Test3", "Tester3", 300, true);

    @BeforeEach
    void initMovieService() {
        movieService = new MovieService(new MovieRepoAdapter(new MovieEntRepo()));
        movieService.add(tester);
    }

    @Test
    void getAllMovies() {
        List<Movie> movieList = movieService.getAll();
        assertFalse(movieList.isEmpty());
    }

    @Test
    void addMovie() {
        int size = movieService.getAll().size();
        movieService.add(tester2);
        assertEquals(size+1, movieService.getAll().size());
    }

    @Test
    void setMovieRented() {
        Movie exampleMovie = movieService.getAll().get(0);
        assertFalse(exampleMovie.isRented());
        movieService.setMovieRented(exampleMovie,true);
        assertTrue(movieService.getViaUUID(exampleMovie.getId()).isRented());
    }

    @Test
    void removeMovie() {
        int size = movieService.getAll().size();
        Movie exampleMovie = movieService.getAll().get(0);
        movieService.remove(exampleMovie);
        assertEquals(size-1, movieService.getAll().size());
    }

    @Test
    void getMovieViaUUID() {
        Movie exampleMovie = movieService.getAll().get(0);
        Movie exampleMovieByID = movieService.getViaUUID(exampleMovie.getId());
        assertEquals(exampleMovie,exampleMovieByID);
    }

    @Test
    void updateSingleMovie() {
        Movie exampleMovie = movieService.getAll().get(0);
        movieService.update(exampleMovie, tester2);
        Movie foundMovie = movieService.getViaUUID(exampleMovie.getId());
        assertEquals(tester2.getTitle(), foundMovie.getTitle());
    }
}