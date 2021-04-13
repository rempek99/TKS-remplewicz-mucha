package pl.lodz.p.it.soaptests.aggregates.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.lodz.p.it.applicationcore.domainmodel.model.Book;
import pl.lodz.p.it.soap.aggregates.converters.BookSoapConverter;
import pl.lodz.p.it.soap.model.BookSoap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookSoapConverterTest {

    private final BookSoap bookSoap = new BookSoap("Test", "pl.lodz.p.it.viewports.book", 100,false);
    private final Book book = new Book("Test", "pl.lodz.p.it.viewports.book", 100,false);

    @BeforeEach
    void init() {
        bookSoap.setId("a9f3ae39-7697-4cdc-8773-e6929656af59");
        book.setId("a9f3ae39-7697-4cdc-8773-e6929656af59");
    }

    @Test
    void convertBookToSoap() {
        BookSoap check = BookSoapConverter.convertBookToBookSoap(book);
        assertEquals(check, bookSoap);
    }

    @Test
    void convertSoapToBook() {
        Book check = BookSoapConverter.convertBookSoapToBook(bookSoap);
        assertEquals(check, book);
    }
}