package pl.lodz.p.it.rentrepositoriesadapters.aggregates.converters;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.*;
import pl.lodz.p.it.rentrepositoriesadapters.model_ent.entities.ClientEnt;
import pl.lodz.p.it.rentrepositoriesadapters.model_ent.entities.BookEnt;
import pl.lodz.p.it.rentrepositoriesadapters.model_ent.entities.BookRentalEnt;

import java.util.Date;
import java.util.List;

public class BookRentalConverter {

    private BookRentalConverter() {
    }

    public static BookRentalEnt convertBookRentalToEnt(BookRental bookRental) {
        Book book = bookRental.getBook();
        BookEnt bookEnt = BookConverter.convertBookToEnt(book);
        Client account = bookRental.getAccount();
        ClientEnt accountEnt = ClientConverter.convertClientToEnt(account);
        String id = bookRental.getId();
        List<Date> range = bookRental.getRange();
        Date rentalStart = bookRental.getRentalStart();
        Date rentalEnd = bookRental.getRentalEnd();

        BookRentalEnt newBookRental = new BookRentalEnt(bookEnt, accountEnt);
        newBookRental.setId(id);
        range.forEach(x -> newBookRental.getRange().add(x));
        newBookRental.setRentalStart(rentalStart);
        newBookRental.setRentalEnd(rentalEnd);

        return newBookRental;
    }

    public static BookRental convertEntToBookRental(BookRentalEnt bookRental) {
        BookEnt bookEnt = bookRental.getBookEnt();
        Book book = BookConverter.convertEntToBook(bookEnt);
        ClientEnt accountEnt = bookRental.getAccountEnt();
        Client account = ClientConverter.convertEntToClient(accountEnt);
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
