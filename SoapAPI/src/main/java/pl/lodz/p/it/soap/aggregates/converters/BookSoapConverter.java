package pl.lodz.p.it.soap.aggregates.converters;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Book;
import pl.lodz.p.it.soap.model.BookSoap;

public class BookSoapConverter {

    private BookSoapConverter() {
    }

    public static BookSoap convertBookToBookSoap(Book book){

        BookSoap newBook = new BookSoap(
                book.getTitle(),
                book.getAuthor(),
                book.getPages(),
                book.isRented());
        newBook.setId(book.getId());
        return newBook;
    }

    public static Book convertBookSoapToBook(BookSoap book){
        Book newBook = new Book(
                book.getTitle(),
                book.getAuthor(),
                book.getPages(),
                book.isRented());
        newBook.setId(book.getId());
        return newBook;
    }
}
