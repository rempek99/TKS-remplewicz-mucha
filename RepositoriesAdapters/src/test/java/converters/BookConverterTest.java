package converters;

import aggregates.converters.BookConverter;
import model.Book;
import model_ent.entities.BookEnt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookConverterTest {

    private final BookEnt bookEnt = new BookEnt("Test", "book", 100,false);
    private final Book book = new Book("Test", "book", 100,false);

    @BeforeEach
    void init() {
        bookEnt.setId("212c5de0-ad07-444a-9100-fd422b4bff93");
        book.setId("212c5de0-ad07-444a-9100-fd422b4bff93");
    }

    @Test
    void convertBookToEnt() {
        BookEnt check = BookConverter.convertBookToEnt(book);
        assertEquals(check, bookEnt);
    }

    @Test
    void convertEntToBook() {
        Book check = BookConverter.convertEntToBook(bookEnt);
        assertEquals(check, book);
    }
}