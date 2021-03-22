package services;

import aggregates.adapters.BookRepoAdapter;
import infrastructure.BookPort;
import model.Book;
import model_ent.repositories.BookEntRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Incubating;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock(name = "bookPort")
    private BookPort bookPort;


    private final Book tester = new Book("Test", "Tester", 100,false);
    private final Book tester2 = new Book("Test2", "Tester2", 200, true);
    private final Book tester3 = new Book("Test3", "Tester3", 300, true);


    @Test
    void getAllBooks() {
        //given
        //when
        List<Book> bookList = bookService.getAll();
        //then
        then(bookPort).should().getAllBooks();
        then(bookPort).shouldHaveNoMoreInteractions();
    }

    @Test
    void updateSingleBook() {
        //given
        Book exampleBook = tester2;
        //when
        bookService.update(exampleBook, tester3);
        //then
        then(bookPort).should().updateSingleBook(exampleBook,tester3);
    }

    @Test
    void addBook() {
        //given
        Book exampleBook = tester;
        //when
        bookService.add(exampleBook);
        //then
        then(bookPort).should().addBook(exampleBook);
        then(bookPort).shouldHaveNoMoreInteractions();
    }

    @Test
    void setBookRented() {
        //given
        Book exampleBook = tester;
        assertFalse(exampleBook.isRented());
        //when
        bookService.setBookRented(exampleBook,true);
        //then
        then(bookPort).should().setBookRented(exampleBook,true);
        then(bookPort).shouldHaveNoMoreInteractions();
    }

    @Test
    void removeBook() {
        //given
        Book exampleBook = tester3;
        //when
        bookService.remove(exampleBook);
        then(bookPort).should().removeBook(exampleBook);
    }

    @Test
    void getBookViaUUID() {
        //given
        Book exampleBook = tester2;
        given(bookPort.getBookViaUUID(exampleBook.getId())).willReturn(exampleBook);
        //when
        Book exampleBookByID = bookService.getViaUUID(exampleBook.getId());
        //then
        then(bookPort).should().getBookViaUUID(exampleBook.getId());
        assertEquals(exampleBook,exampleBookByID);
    }
}