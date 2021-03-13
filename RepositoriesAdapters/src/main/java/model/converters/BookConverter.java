package model.converters;

import model.entities.*;
import model.*;

import java.util.Date;

public class BookConverter {
    public static BookEnt convertBookToEnt(Book book){
        String title = book.getTitle();
        String author = book.getAuthor();
        int pages = book.getPages();
        boolean rented = book.getRented();
        String id = book.getId();
        String rentalUserUUID = book.getRentalUserUUID();
        Date rentalStart = book.getRentalStart();
        Date rentalEnd = book.getRentalEnd();

        BookEnt newBook = new BookEnt(title, author, pages, rented);
        newBook.setId(id);
        newBook.setRentalUserUUID(rentalUserUUID);
        newBook.setRentalStart(rentalStart);
        newBook.setRentalEnd(rentalEnd);

        return newBook;
    }

    public static Book convertEntToBook(BookEnt book){
        String title = book.getTitle();
        String author = book.getAuthor();
        int pages = book.getPages();
        boolean rented = book.getRented();
        String id = book.getId();
        String rentalUserUUID = book.getRentalUserUUID();
        Date rentalStart = book.getRentalStart();
        Date rentalEnd = book.getRentalEnd();

        Book newBook = new Book(title, author, pages, rented);
        newBook.setId(id);
        newBook.setRentalUserUUID(rentalUserUUID);
        newBook.setRentalStart(rentalStart);
        newBook.setRentalEnd(rentalEnd);

        return newBook;
    }
}
