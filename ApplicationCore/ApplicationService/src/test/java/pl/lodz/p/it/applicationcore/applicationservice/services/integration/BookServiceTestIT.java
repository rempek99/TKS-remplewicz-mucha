package pl.lodz.p.it.applicationcore.applicationservice.services.integration;

import pl.lodz.p.it.repositoriesadapters.aggregates.adapters.BookRepoAdapter;
import pl.lodz.p.it.applicationcore.domainmodel.model.Book;
import pl.lodz.p.it.repositoriesadapters.model_ent.repositories.BookEntRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.lodz.p.it.applicationcore.applicationservice.services.BookService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTestIT {

    private BookService bookService;
    private final Book tester = new Book("Test", "Tester", 100,false);
    private final Book tester2 = new Book("Test2", "Tester2", 200, true);
    private final Book tester3 = new Book("Test3", "Tester3", 300, true);

    @BeforeEach
    void initBookService() {
        bookService = new BookService(new BookRepoAdapter(new BookEntRepo()));
        bookService.add(tester);
    }

    @Test
    void getAllBooks() {
        List<Book> bookList = bookService.getAll();
        assertFalse(bookList.isEmpty());
    }

    @Test
    void updateSingleBook() {
        Book exampleBook = bookService.getAll().get(0);
        bookService.update(exampleBook, tester2);
        Book foundBook = bookService.getViaUUID(exampleBook.getId());
        assertEquals(tester2.getTitle(), foundBook.getTitle());
    }

    @Test
    void addBook() {
        int size = bookService.getAll().size();
        bookService.add(tester2);
        assertEquals(size+1, bookService.getAll().size());
    }

    @Test
    void setBookRented() {
        Book exampleBook = bookService.getAll().get(0);
        assertFalse(exampleBook.isRented());
        bookService.setBookRented(exampleBook,true);
        assertTrue(bookService.getViaUUID(exampleBook.getId()).isRented());
    }

    @Test
    void removeBook() {
        int size = bookService.getAll().size();
        Book exampleBook = bookService.getAll().get(0);
        bookService.remove(exampleBook);
        assertEquals(size-1, bookService.getAll().size());
    }

    @Test
    void getBookViaUUID() {
        Book exampleBook = bookService.getAll().get(0);
        Book exampleBookByID = bookService.getViaUUID(exampleBook.getId());
        assertEquals(exampleBook,exampleBookByID);
    }
}