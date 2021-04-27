package pl.p.lodz.it.RestTest.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Account;
import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Book;
import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.BookRental;
import pl.lodz.p.it.viewadapters.converters.BookRentalConverter;
import pl.lodz.p.it.viewmodel.modelDTO.AccountDTO;
import pl.lodz.p.it.viewmodel.modelDTO.BookDTO;
import pl.lodz.p.it.viewmodel.modelDTO.BookRentalDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookRentalRestConverterTest {

    private final AccountDTO accountDTO = new AccountDTO("test", "Testowy", "user", true, "test", "test123");
    private final BookDTO bookDTO = new BookDTO("Test", "test", 100,false);
    private final BookRentalDTO bookRentalDTO = new BookRentalDTO(bookDTO, accountDTO);
    private final Account account= new Account("test", "Testowy", "user", true, "test", "test123");
    private final Book book = new Book("Test", "test", 100,false);
    private final BookRental bookRental = new BookRental(book, account);

    @BeforeEach
    void init() {
        accountDTO.setId("212c5de0-ad07-444a-9100-fd422b4bff93");
        account.setId("212c5de0-ad07-444a-9100-fd422b4bff93");
        bookDTO.setId("788e5f46-e7a0-4da3-98cc-9bd2d791698f");
        book.setId("788e5f46-e7a0-4da3-98cc-9bd2d791698f");
        bookRentalDTO.setId("2dff585d-e208-4d47-a464-e4be9e0c6290");
        bookRental.setId("2dff585d-e208-4d47-a464-e4be9e0c6290");
    }

    @Test
    void convertBookRentalToEnt() {
        BookRentalDTO check = BookRentalConverter.convertBookRentalToDTO(bookRental);
        assertEquals(check, bookRentalDTO);
    }

    @Test
    void convertEntToBookRental() {
        BookRental check = BookRentalConverter.convertDTOToBookRental(bookRentalDTO);
        assertEquals(check, bookRental);
    }
}