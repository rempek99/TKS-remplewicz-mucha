package repositories;

import model.entities.BookEnt;
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
    void initRepo() {
        repo = new BookEntRepo();
        assertTrue(repo.getAllBooks().isEmpty());
        repo.addBook(tester);
        repo.addBook(tester2);
        repo.addBook(tester3);
    }

    @Test
    void getAllBooks() {
        assertFalse(repo.getAllBooks().isEmpty());
        assertEquals(NUMBER_OF_TESTERS, repo.getAllBooks().size());
    }

    @Test
    void addBook() {
        final int repo_size = repo.getAllBooks().size();
        BookEnt my_book = new BookEnt("Moja ksiazka", "Remplewicz", 1000, false);
        repo.addBook(my_book);
        assertEquals(repo_size + 1, repo.getAllBooks().size());
    }

    @Test
    void removeBook() {
        final int repo_size = repo.getAllBooks().size();
        repo.removeBook(tester3);
        assertEquals(repo_size - 1, repo.getAllBooks().size());
    }

    @Test
    void getBookViaUUID() {
        BookEnt my_book = new BookEnt("Moja ksiazka", "Remplewicz", 1000, false);
        BookEnt added = repo.addBook(my_book);
        BookEnt found = repo.getBookViaUUID(added.getId());
        assertEquals(my_book, found);
    }

    @Test
    void getBook() {
        BookEnt my_book = new BookEnt("Moja ksiazka", "Remplewicz", 1000, false);
        repo.addBook(my_book);
        BookEnt found = repo.getBook(my_book);
        assertEquals(my_book, found);
    }

    @Test
    void updateSingleBook() {
        BookEnt my_book = new BookEnt("Moja ksiazka", "Remplewicz", 1000, false);
        BookEnt updated = repo.updateSingleBook(tester,my_book);
        assertEquals(my_book.getAuthor(), updated.getAuthor());
        assertNotEquals(my_book.getId(), updated.getId());
    }
}