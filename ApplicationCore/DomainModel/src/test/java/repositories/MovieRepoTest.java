package repositories;

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
        assertTrue(repo.getAll().isEmpty());
        repo.add(tester);
        repo.add(tester2);
        repo.add(tester3);
    }


    @Test
    void getAllMovies() {
        assertFalse(repo.getAll().isEmpty());
        assertEquals(NUMBER_OF_TESTERS, repo.getAll().size());
    }

    @Test
    void addMovie() {
        final int repo_size = repo.getAll().size();
        Movie my_movie = new Movie("Moj film", "Remplewicz", 1000, false);
        repo.add(my_movie);
        assertEquals(repo_size + 1, repo.getAll().size());
    }

    @Test
    void removeMovie() {
        final int repo_size = repo.getAll().size();
        repo.remove(tester3);
        assertEquals(repo_size - 1, repo.getAll().size());
    }

    @Test
    void getMovieViaUUID() {
        Movie my_movie = new Movie("Moj film", "Remplewicz", 1000, false);
        Movie added = repo.add(my_movie);
        Movie found = repo.getViaUUID(added.getId());
        assertEquals(my_movie, found);
    }

    @Test
    void getMovie() {
        Movie my_movie = new Movie("Moj film", "Remplewicz", 1000, false);
        repo.add(my_movie);
        Movie found = repo.get(my_movie);
        assertEquals(my_movie, found);
    }

    @Test
    void updateSingleMovie() {
        Movie my_movie = new Movie("Moj film", "Remplewicz", 1000, false);
        Movie updated = repo.update(tester,my_movie);
        assertEquals(my_movie.getAuthor(), updated.getAuthor());
        assertNotEquals(my_movie.getId(), updated.getId());
    }
}