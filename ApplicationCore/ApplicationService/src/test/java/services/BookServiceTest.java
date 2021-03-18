package services;

import model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    private BookService bookService;
    private final Book tester = new Book("Test", "Tester", 100,false);
    private final Book tester2 = new Book("Test2", "Tester2", 200, true);
    private final Book tester3 = new Book("Test3", "Tester3", 300, true);

    @BeforeEach
    void initBookService() {
       // bookService = new BookService(new BookRepoAdapter(new BookEntRepo()));
        bookService.addBook(tester);
    }

    @Test
    void getAllBooks() {
        List<Book> bookList = bookService.getAllBooks();
        assertFalse(bookList.isEmpty());
    }

    @Test
    void updateSingleBook() {
        Book exampleBook = bookService.getAllBooks().get(0);
        bookService.updateSingleBook(exampleBook, tester2);
        Book foundBook = bookService.getBookViaUUID(exampleBook.getId());
        assertEquals(tester2.getTitle(), foundBook.getTitle());
    }

    @Test
    void addBook() {
        int size = bookService.getAllBooks().size();
        bookService.addBook(tester2);
        assertEquals(size+1, bookService.getAllBooks().size());
    }

    @Test
    void setBookRented() {
        Book exampleBook = bookService.getAllBooks().get(0);
        assertFalse(exampleBook.isRented());
        bookService.setBookRented(exampleBook,true);
        assertTrue(bookService.getBookViaUUID(exampleBook.getId()).isRented());
    }

    @Test
    void removeBook() {
        int size = bookService.getAllBooks().size();
        Book exampleBook = bookService.getAllBooks().get(0);
        bookService.removeBook(exampleBook);
        assertEquals(size-1, bookService.getAllBooks().size());
    }

    @Test
    void getBookViaUUID() {
        Book exampleBook = bookService.getAllBooks().get(0);
        Book exampleBookByID = bookService.getBookViaUUID(exampleBook.getId());
        assertEquals(exampleBook,exampleBookByID);
    }
}