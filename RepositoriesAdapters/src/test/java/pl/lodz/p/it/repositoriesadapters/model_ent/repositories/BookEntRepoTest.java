package pl.lodz.p.it.repositoriesadapters.model_ent.repositories;

import pl.lodz.p.it.repositoriesadapters.model_ent.entities.BookEnt;
import pl.lodz.p.it.repositoriesadapters.model_ent.repositories.BookEntRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookEntRepoTest {

    private static final int NUMBER_OF_TESTERS = 3;

    private BookEntRepo repo;
    private final BookEnt tester = new BookEnt("Test", "Tester", 100,false);
    private final BookEnt tester2 = new BookEnt("Test2", "Tester2", 200, true);
    private final BookEnt tester3 = new BookEnt("Test3", "Tester3", 300, true);


    @BeforeEach
    void initRepo() throws RepositoryException {
        repo = new BookEntRepo();
        assertTrue(repo.getAll().isEmpty());
        repo.add(tester);
        repo.add(tester2);
        repo.add(tester3);
    }

    @Test
    void getAllBooks() {
        assertFalse(repo.getAll().isEmpty());
        assertEquals(NUMBER_OF_TESTERS, repo.getAll().size());
    }

    @Test
    void addBook() throws RepositoryException {
        final int repo_size = repo.getAll().size();
        BookEnt my_book = new BookEnt("Moja ksiazka", "Remplewicz", 1000, false);
        repo.add(my_book);
        assertEquals(repo_size + 1, repo.getAll().size());
    }

    @Test
    void removeBook() {
        final int repo_size = repo.getAll().size();
        repo.remove(tester3);
        assertEquals(repo_size - 1, repo.getAll().size());
    }

    @Test
    void getBookViaUUID() throws RepositoryException {
        BookEnt my_book = new BookEnt("Moja ksiazka", "Remplewicz", 1000, false);
        BookEnt added = repo.add(my_book);
        BookEnt found = repo.getViaUUID(added.getId());
        assertEquals(my_book, found);
    }

    @Test
    void getBook() throws RepositoryException {
        BookEnt my_book = new BookEnt("Moja ksiazka", "Remplewicz", 1000, false);
        repo.add(my_book);
        BookEnt found = repo.get(my_book);
        assertEquals(my_book, found);
    }

    @Test
    void updateSingleBook() {
        BookEnt my_book = new BookEnt("Moja ksiazka", "Remplewicz", 1000, false);
        BookEnt updated = repo.update(tester,my_book);
        assertEquals(my_book.getAuthor(), updated.getAuthor());
        assertNotEquals(my_book.getId(), updated.getId());
    }
}