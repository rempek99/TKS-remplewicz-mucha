package pl.lodz.p.it.applicationcore.domainmodel.repositories;

import pl.lodz.p.it.applicationcore.domainmodel.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookRepoTest {

    private static final int NUMBER_OF_TESTERS = 3;

    private BookRepo repo;
    private final Book tester = new Book("Test", "Tester", 100,false);
    private final Book tester2 = new Book("Test2", "Tester2", 200, true);
    private final Book tester3 = new Book("Test3", "Tester3", 300, true);


    @BeforeEach
    void initRepo() {
        repo = new BookRepo();
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
    void addBook() {
        final int repo_size = repo.getAll().size();
        Book my_book = new Book("Moja ksiazka", "Remplewicz", 1000, false);
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
    void getBookViaUUID() {
        Book my_book = new Book("Moja ksiazka", "Remplewicz", 1000, false);
        Book added = repo.add(my_book);
        Book found = repo.getViaUUID(added.getId());
        assertEquals(my_book, found);
    }

    @Test
    void getBook() {
        Book my_book = new Book("Moja ksiazka", "Remplewicz", 1000, false);
        repo.add(my_book);
        Book found = repo.get(my_book);
        assertEquals(my_book, found);
    }

    @Test
    void updateSingleBook() {
        Book my_book = new Book("Moja ksiazka", "Remplewicz", 1000, false);
        Book updated = repo.update(tester,my_book);
        assertEquals(my_book.getAuthor(), updated.getAuthor());
        assertNotEquals(my_book.getId(), updated.getId());
    }
}