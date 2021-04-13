package pl.lodz.p.it.soaptests.aggregates.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.lodz.p.it.applicationcore.domainmodel.model.Account;
import pl.lodz.p.it.applicationcore.domainmodel.model.Book;
import pl.lodz.p.it.applicationcore.domainmodel.model.BookRental;
import pl.lodz.p.it.soap.aggregates.converters.BookRentalSoapConverter;
import pl.lodz.p.it.soap.model.AccountSoap;
import pl.lodz.p.it.soap.model.BookRentalSoap;
import pl.lodz.p.it.soap.model.BookSoap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookRentalSoapConverterTest {

    private final AccountSoap accountSoap = new AccountSoap("test", "Testowy", "user", true, "test", "test123");
    private final Account account= new Account("test", "Testowy", "user", true, "test", "test123");
    private final BookSoap bookSoap = new BookSoap("Test", "test", 100,false);
    private final Book book = new Book("Test", "test", 100,false);
    private final BookRentalSoap bookRentalSoap = new BookRentalSoap(bookSoap, accountSoap);
    private final BookRental bookRental = new BookRental(book, account);

    @BeforeEach
    void init() {
        accountSoap.setId("212c5de0-ad07-444a-9100-fd422b4bff93");
        account.setId("212c5de0-ad07-444a-9100-fd422b4bff93");
        bookSoap.setId("788e5f46-e7a0-4da3-98cc-9bd2d791698f");
        book.setId("788e5f46-e7a0-4da3-98cc-9bd2d791698f");
        bookRentalSoap.setId("2dff585d-e208-4d47-a464-e4be9e0c6290");
        bookRental.setId("2dff585d-e208-4d47-a464-e4be9e0c6290");
    }

    @Test
    void convertBookRentalToEnt() {
        BookRentalSoap check = BookRentalSoapConverter.convertBookRentalToBookRentalSoap(bookRental);
        assertEquals(check, bookRentalSoap);
    }

    @Test
    void convertEntToBookRental() {
        BookRental check = BookRentalSoapConverter.convertBookRentalSoapToBookRental(bookRentalSoap);
        assertEquals(check, bookRental);
    }
}