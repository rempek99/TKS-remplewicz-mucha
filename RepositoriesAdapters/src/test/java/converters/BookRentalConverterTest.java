package converters;

import aggregates.converters.BookRentalConverter;
import model.Account;
import model.Book;
import model.BookRental;
import model_ent.entities.AccountEnt;
import model_ent.entities.BookEnt;
import model_ent.entities.BookRentalEnt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookRentalConverterTest {

    private final AccountEnt accountEnt = new AccountEnt("test", "Testowy", "user", true, "test", "test123");
    private final Account account= new Account("test", "Testowy", "user", true, "test", "test123");
    private final BookEnt bookEnt = new BookEnt("Test", "test", 100,false);
    private final Book book = new Book("Test", "test", 100,false);
    private final BookRentalEnt bookRentalEnt = new BookRentalEnt(bookEnt, accountEnt);
    private final BookRental bookRental = new BookRental(book, account);

    @BeforeEach
    void init() {
        accountEnt.setId("212c5de0-ad07-444a-9100-fd422b4bff93");
        account.setId("212c5de0-ad07-444a-9100-fd422b4bff93");
        bookEnt.setId("788e5f46-e7a0-4da3-98cc-9bd2d791698f");
        book.setId("788e5f46-e7a0-4da3-98cc-9bd2d791698f");
        bookRentalEnt.setId("2dff585d-e208-4d47-a464-e4be9e0c6290");
        bookRental.setId("2dff585d-e208-4d47-a464-e4be9e0c6290");
    }

    @Test
    void convertBookRentalToEnt() {
        BookRentalEnt check = BookRentalConverter.convertBookRentalToEnt(bookRental);
        assertEquals(check, bookRentalEnt);
    }

    @Test
    void convertEntToBookRental() {
        BookRental check = BookRentalConverter.convertEntToBookRental(bookRentalEnt);
        assertEquals(check, bookRental);
    }
}