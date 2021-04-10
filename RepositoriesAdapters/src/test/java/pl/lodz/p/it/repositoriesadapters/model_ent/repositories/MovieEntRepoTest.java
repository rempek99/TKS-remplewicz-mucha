package pl.lodz.p.it.repositoriesadapters.model_ent.repositories;

import pl.lodz.p.it.repositoriesadapters.model_ent.entities.MovieEnt;
import pl.lodz.p.it.repositoriesadapters.model_ent.repositories.MovieEntRepo;
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
        MovieEnt my_movie = new MovieEnt("Moj film", "Remplewicz", 1000, false);
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
        MovieEnt my_movie = new MovieEnt("Moj film", "Remplewicz", 1000, false);
        MovieEnt added = repo.add(my_movie);
        MovieEnt found = repo.getViaUUID(added.getId());
        assertEquals(my_movie, found);
    }

    @Test
    void getMovie() {
        MovieEnt my_movie = new MovieEnt("Moj film", "Remplewicz", 1000, false);
        repo.add(my_movie);
        MovieEnt found = repo.get(my_movie);
        assertEquals(my_movie, found);
    }

    @Test
    void updateSingleMovie() {
        MovieEnt my_movie = new MovieEnt("Moj film", "Remplewicz", 1000, false);
        MovieEnt updated = repo.update(tester,my_movie);
        assertEquals(my_movie.getAuthor(), updated.getAuthor());
        assertNotEquals(my_movie.getId(), updated.getId());
    }
}