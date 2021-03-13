package model.converters;

import model.entities.*;
import model.*;

import java.util.Date;
import java.util.List;

public class BookRentalConverter {
    public static BookRentalEnt convertBookRentalToEnt(BookRental bookRental) {
        Book book = bookRental.getBook();
        BookConverter bookConverter = new BookConverter();
        BookEnt bookEnt = bookConverter.convertBookToEnt(book);
        Account account = bookRental.getAccount();
        AccountConverter accountConverter = new AccountConverter();
        AccountEnt accountEnt = accountConverter.convertAccountToEnt(account);
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
        BookEnt bookEnt = bookRental.getBook();
        BookConverter bookConverter = new BookConverter();
        Book book = bookConverter.convertEntToBook(bookEnt);
        AccountEnt accountEnt = bookRental.getAccount();
        AccountConverter accountConverter = new AccountConverter();
        Account account = accountConverter.convertEntToAccount(accountEnt);
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
