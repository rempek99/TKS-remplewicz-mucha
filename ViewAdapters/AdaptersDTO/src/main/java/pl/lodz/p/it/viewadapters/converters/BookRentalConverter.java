package pl.lodz.p.it.viewadapters.converters;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Account;
import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Book;
import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.BookRental;
import pl.lodz.p.it.viewmodel.modelDTO.AccountDTO;
import pl.lodz.p.it.viewmodel.modelDTO.BookDTO;
import pl.lodz.p.it.viewmodel.modelDTO.BookRentalDTO;

import java.util.Date;
import java.util.List;

public class BookRentalConverter {

    private BookRentalConverter() {
    }

    public static BookRentalDTO convertBookRentalToDTO(BookRental bookRental) {
        Book book = bookRental.getBook();
        BookDTO bookDTO = BookConverter.convertBookToDTO(book);
        Account account = bookRental.getAccount();
        AccountDTO accountDTO = AccountConverter.convertAccountToDTO(account);
        String id = bookRental.getId();
        List<Date> range = bookRental.getRange();
        Date rentalStart = bookRental.getRentalStart();
        Date rentalEnd = bookRental.getRentalEnd();

        BookRentalDTO newBookRental = new BookRentalDTO(bookDTO, accountDTO);
        newBookRental.setId(id);
        newBookRental.setRange(range);
        newBookRental.setRentalStart(rentalStart);
        newBookRental.setRentalEnd(rentalEnd);

        return newBookRental;
    }

    public static BookRental convertDTOToBookRental(BookRentalDTO bookRental) {
        BookDTO bookDTO = bookRental.getBook();
        Book book = BookConverter.convertDTOToBook(bookDTO);
        AccountDTO accountDTO = bookRental.getAccount();
        Account account = AccountConverter.convertDTOToAccount(accountDTO);
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