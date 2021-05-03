package pl.lodz.p.it.rentapplicationapi.aggregates.converters;

import pl.lodz.p.it.rentapplicationapi.rentviewmodel.modelDTO.BookDTO;
import pl.lodz.p.it.rentapplicationapi.rentviewmodel.modelDTO.BookRentalDTO;
import pl.lodz.p.it.rentapplicationapi.rentviewmodel.modelDTO.ClientDTO;
import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Client;
import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Book;
import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.BookRental;


import java.util.Date;
import java.util.List;

public class BookRentalConverter {

    private BookRentalConverter() {
    }

    public static BookRentalDTO convertBookRentalToDTO(BookRental bookRental) {
        Book book = bookRental.getBook();
        BookDTO bookDTO = BookConverter.convertBookToDTO(book);
        Client account = bookRental.getAccount();
        ClientDTO accountDTO = ClientConverter.convertClientToDTO(account);
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
        ClientDTO accountDTO = bookRental.getAccount();
        Client account = ClientConverter.convertDTOToClient(accountDTO);
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