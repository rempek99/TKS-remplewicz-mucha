package model.repositories;

import model.Book;
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
        Book my_book = new Book("Moja ksiazka", "Remplewicz", 1000, false);
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
        Book my_book = new Book("Moja ksiazka", "Remplewicz", 1000, false);
        Book added = repo.addBook(my_book);
        Book found = repo.getBookViaUUID(added.getId());
        assertEquals(my_book, found);
    }

    @Test
    void getBook() {
        Book my_book = new Book("Moja ksiazka", "Remplewicz", 1000, false);
        repo.addBook(my_book);
        Book found = repo.getBook(my_book);
        assertEquals(my_book, found);
    }

    @Test
    void updateSingleBook() {
        Book my_book = new Book("Moja ksiazka", "Remplewicz", 1000, false);
        Book updated = repo.updateSingleBook(tester,my_book);
        assertEquals(my_book.getAuthor(), updated.getAuthor());
        assertNotEquals(my_book.getId(), updated.getId());
    }
}