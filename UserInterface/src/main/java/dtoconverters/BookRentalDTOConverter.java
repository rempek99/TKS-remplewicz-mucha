package dtoconverters;

import dtomodel.*;
import model.Account;
import model.Book;
import model.BookRental;

import java.util.Date;
import java.util.List;

public class BookRentalDTOConverter {
    public static BookRentalDTO convertBookRentalToDTO(BookRental bookRental) {
        Book book = bookRental.getBook();
        BookDTOConverter bookConverter = new BookDTOConverter();
        BookDTO bookDTO = bookConverter.convertBookToDTO(book);
        Account account = bookRental.getAccount();
        AccountDTOConverter accountConverter = new AccountDTOConverter();
        AccountDTO accountDTO = accountConverter.convertAccountToDTO(account);
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
        BookDTOConverter bookConverter = new BookDTOConverter();
        Book book = bookConverter.convertDTOToBook(bookDTO);
        AccountDTO accountDTO = bookRental.getAccount();
        AccountDTOConverter accountConverter = new AccountDTOConverter();
        Account account = accountConverter.convertDTOToAccount(accountDTO);
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
