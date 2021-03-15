package aggregates.converters;

import model.*;
import model_ent.entities.BookEnt;

import java.util.Date;

public class BookConverter {

    private BookConverter() {
    }

    public static BookEnt convertBookToEnt(Book book){
        String title = book.getTitle();
        String author = book.getAuthor();
        int pages = book.getPages();
        boolean rented = book.isRented();
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
        boolean rented = book.isRented();
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
