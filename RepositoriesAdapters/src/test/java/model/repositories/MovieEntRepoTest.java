package model.repositories;

import model.entities.MovieEnt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieEntRepoTest {

    private static final int NUMBER_OF_TESTERS = 3;

    private MovieEntRepo repo;
    private final MovieEnt tester = new MovieEnt("Test", "Tester", 100,false);
    private final MovieEnt tester2 = new MovieEnt("Test2", "Tester2", 200, true);
    private final MovieEnt tester3 = new MovieEnt("Test3", "Tester3", 300, true);


    @BeforeEach
    void initRepo() {
        repo = new MovieEntRepo();
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
        MovieEnt my_movie = new MovieEnt("Moj film", "Remplewicz", 1000, false);
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
        MovieEnt my_movie = new MovieEnt("Moj film", "Remplewicz", 1000, false);
        MovieEnt added = repo.addMovie(my_movie);
        MovieEnt found = repo.getMovieViaUUID(added.getId());
        assertEquals(my_movie, found);
    }

    @Test
    void getMovie() {
        MovieEnt my_movie = new MovieEnt("Moj film", "Remplewicz", 1000, false);
        repo.addMovie(my_movie);
        MovieEnt found = repo.getMovie(my_movie);
        assertEquals(my_movie, found);
    }

    @Test
    void updateSingleMovie() {
        MovieEnt my_movie = new MovieEnt("Moj film", "Remplewicz", 1000, false);
        MovieEnt updated = repo.updateSingleMovie(tester,my_movie);
        assertEquals(my_movie.getAuthor(), updated.getAuthor());
        assertNotEquals(my_movie.getId(), updated.getId());
    }
}