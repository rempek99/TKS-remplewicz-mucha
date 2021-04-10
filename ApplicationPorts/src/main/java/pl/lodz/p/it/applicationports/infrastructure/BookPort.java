package pl.lodz.p.it.applicationports.infrastructure;

import pl.lodz.p.it.applicationcore.domainmodel.model.Book;
import java.util.List;

public interface BookPort {
    Book getBook(Book b);
    List<Book> getAllBooks();
    void addBook(Book b);
    void setBookRented(Book b, boolean value);
    void removeBook(Book b);
    void updateSingleBook(Book income, Book outcome);
    Book getBookViaUUID(String str);
}
