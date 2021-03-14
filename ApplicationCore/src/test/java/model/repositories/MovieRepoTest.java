package model.repositories;

import model.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieRepoTest {

    private static final int NUMBER_OF_TESTERS = 3;

    private MovieRepo repo;
    private final Movie tester = new Movie("Test", "Tester", 100,false);
    private final Movie tester2 = new Movie("Test2", "Tester2", 200, true);
    private final Movie tester3 = new Movie("Test3", "Tester3", 300, true);


    @BeforeEach
    void initRepo() {
        repo = new MovieRepo();
        assertTrue(repo.getAllMovies().isEmpty());
        repo.addMovie(tester);
        repo.addMovie(tester2);
        repo.addMovie(tester3);
    }


    @Test
    void getAllMovies() {
        assertFalse(repo.getAllMovies().isEmpty());
        assertEquals(NUMBER_OF_TESTERS, repo.getAllMovies().size());
    }

    @Test
    void addMovie() {
        final int repo_size = repo.getAllMovies().size();
        Movie my_movie = new Movie("Moj film", "Remplewicz", 1000, false);
        repo.addMovie(my_movie);
        assertEquals(repo_size + 1, repo.getAllMovies().size());
    }

    @Test
    void removeMovie() {
        final int repo_size = repo.getAllMovies().size();
        repo.removeMovie(tester3);
        assertEquals(repo_size - 1, repo.getAllMovies().size());
    }

    @Test
    void getMovieViaUUID() {
        Movie my_movie = new Movie("Moj film", "Remplewicz", 1000, false);
        Movie added = repo.addMovie(my_movie);
        Movie found = repo.getMovieViaUUID(added.getId());
        assertEquals(my_movie, found);
    }

    @Test
    void getMovie() {
        Movie my_movie = new Movie("Moj film", "Remplewicz", 1000, false);
        repo.addMovie(my_movie);
        Movie found = repo.getMovie(my_movie);
        assertEquals(my_movie, found);
    }

    @Test
    void updateSingleMovie() {
        Movie my_movie = new Movie("Moj film", "Remplewicz", 1000, false);
        Movie updated = repo.updateSingleMovie(tester,my_movie);
        assertEquals(my_movie.getAuthor(), updated.getAuthor());
        assertNotEquals(my_movie.getId(), updated.getId());
    }
}