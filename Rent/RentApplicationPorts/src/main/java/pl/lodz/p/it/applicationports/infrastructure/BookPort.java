package main.java.pl.lodz.p.it.applicationports.infrastructure;

import pl.lodz.p.it.applicationcore.domainmodel.model.Book;
import java.util.List;
import java.util.Optional;

public interface BookPort {
    Book getBook(Book b);
    List<Book> getAllBooks();
    Book addBook(Book b);
    void setBookRented(Book b, boolean value);
    void removeBook(Book b);
    void updateSingleBook(Book income, Book outcome);
    Optional<Book> getBookViaUUID(String str);
}
