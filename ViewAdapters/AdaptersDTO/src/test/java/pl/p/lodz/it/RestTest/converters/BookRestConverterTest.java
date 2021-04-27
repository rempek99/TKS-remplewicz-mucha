package pl.p.lodz.it.RestTest.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Book;
import pl.lodz.p.it.viewadapters.converters.BookConverter;
import pl.lodz.p.it.viewmodel.modelDTO.BookDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookRestConverterTest {

    private final BookDTO bookDTO = new BookDTO("Test", "pl.lodz.p.it.viewports.book", 100,false);
    private final Book book = new Book("Test", "pl.lodz.p.it.viewports.book", 100,false);

    @BeforeEach
    void init() {
        bookDTO.setId("a9f3ae39-7697-4cdc-8773-e6929656af59");
        book.setId("a9f3ae39-7697-4cdc-8773-e6929656af59");
    }

    @Test
    void convertBookToDTO() {
        BookDTO check = BookConverter.convertBookToDTO(book);
        assertEquals(check, bookDTO);
    }

    @Test
    void convertDTOToBook() {
        Book check = BookConverter.convertDTOToBook(bookDTO);
        assertEquals(check, book);
    }
}