package dtoconverters;

import dtomodel.BookDTO;
import model.Book;

import java.util.Date;

public class BookDTOConverter {
    public static BookDTO convertBookToDTO(Book book){
        String title = book.getTitle();
        String author = book.getAuthor();
        int pages = book.getPages();
        boolean rented = book.getRented();
        String id = book.getId();
        String rentalUserUUID = book.getRentalUserUUID();
        Date rentalStart = book.getRentalStart();
        Date rentalEnd = book.getRentalEnd();

        BookDTO newBook = new BookDTO(title, author, pages, rented);
        newBook.setId(id);
        newBook.setRentalUserUUID(rentalUserUUID);
        newBook.setRentalStart(rentalStart);
        newBook.setRentalEnd(rentalEnd);

        return newBook;
    }

    public static Book convertDTOToBook(BookDTO book){
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
