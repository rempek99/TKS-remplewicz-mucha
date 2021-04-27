package pl.lodz.p.it.soap.aggregates.converters;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.BookRental;
import pl.lodz.p.it.soap.model.BookRentalSoap;

public class BookRentalSoapConverter {

    private BookRentalSoapConverter() {
    }

    public static BookRentalSoap convertBookRentalToBookRentalSoap(BookRental bookRental) {
        BookRentalSoap newBookRental = new BookRentalSoap(
                BookSoapConverter.convertBookToBookSoap(bookRental.getBook()),
                AccountSoapConverter.convertAccountToAccountSoap(bookRental.getAccount())
        );
        newBookRental.setId(bookRental.getId());
        bookRental.getRange().forEach(x -> newBookRental.getRange().add(x));
        newBookRental.setRentalStart(bookRental.getRentalStart());
        newBookRental.setRentalEnd(bookRental.getRentalEnd());
        return newBookRental;
    }

    public static BookRental convertBookRentalSoapToBookRental(BookRentalSoap bookRental) {
        BookRental newBookRental = new BookRental(
                BookSoapConverter.convertBookSoapToBook(bookRental.getBook()),
                AccountSoapConverter.convertAccountSoapToAccount(bookRental.getAccount())
        );
        newBookRental.setId(bookRental.getId());
        bookRental.getRange().forEach(x -> newBookRental.getRange().add(x));
        newBookRental.setRentalStart(bookRental.getRentalStart());
        newBookRental.setRentalEnd(bookRental.getRentalEnd());
        return newBookRental;
    }
}
