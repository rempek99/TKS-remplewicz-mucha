package pl.lodz.p.it.rentviewadapters.converters;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Book;
import pl.lodz.p.it.rentviewmodel.modelDTO.BookDTO;

public class BookConverter {

    private BookConverter() {
    }

    public static BookDTO convertBookToDTO(Book book){
        String title = book.getTitle();
        String author = book.getAuthor();
        int pages = book.getPages();
        boolean rented = book.isRented();
        String id = book.getId();

        BookDTO newBook = new BookDTO(title, author, pages, rented);
        newBook.setId(id);

        return newBook;
    }

    public static Book convertDTOToBook(BookDTO book){
        String title = book.getTitle();
        String author = book.getAuthor();
        int pages = book.getPages();
        boolean rented = book.isRented();
        String id = book.getId();

        Book newBook = new Book(title, author, pages, rented);
        newBook.setId(id);

        return newBook;
    }
}