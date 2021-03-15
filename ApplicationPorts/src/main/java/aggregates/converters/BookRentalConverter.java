package aggregates.converters;

import model.*;
import model_ent.entities.AccountEnt;
import model_ent.entities.BookEnt;
import model_ent.entities.BookRentalEnt;

import java.util.Date;
import java.util.List;

public class BookRentalConverter {

    private BookRentalConverter() {
    }

    public static BookRentalEnt convertBookRentalToEnt(BookRental bookRental) {
        Book book = bookRental.getBook();
        BookEnt bookEnt = BookConverter.convertBookToEnt(book);
        Account account = bookRental.getAccount();
        AccountEnt accountEnt = AccountConverter.convertAccountToEnt(account);
        String id = bookRental.getId();
        List<Date> range = bookRental.getRange();
        Date rentalStart = bookRental.getRentalStart();
        Date rentalEnd = bookRental.getRentalEnd();

        BookRentalEnt newBookRental = new BookRentalEnt(bookEnt, accountEnt);
        newBookRental.setId(id);
        newBookRental.setRange(range);
        newBookRental.setRentalStart(rentalStart);
        newBookRental.setRentalEnd(rentalEnd);

        return newBookRental;
    }

    public static BookRental convertEntToBookRental(BookRentalEnt bookRental) {
        BookEnt bookEnt = bookRental.getBookEnt();
        Book book = BookConverter.convertEntToBook(bookEnt);
        AccountEnt accountEnt = bookRental.getAccountEnt();
        Account account = AccountConverter.convertEntToAccount(accountEnt);
        String id = bookRental.getId();
        List<Date> range = bookRental.getRange();
        Date rentalStart = bookRental.getRentalStart();
        Date rentalEnd = bookRental.getRentalEnd();

        BookRental newBookRental = new BookRental(book, account);
        newBookRental.setId(id);
        newBookRental.setRange(range);
        newBookRental.setRentalStart(rentalStart);
        newBookRental.setRentalEnd(rentalEnd);

        return newBookRental;
    }
}
