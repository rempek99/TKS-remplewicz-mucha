package infrastructure;

import model.Book;
import java.util.List;

public interface BookPort {
    Book getBook(Book b);
    List<Book> getAllBooks();
    void addBook(Book b);
    void removeBook(Book b);
    void updateSingleBook(Book income, Book outcome);
    Book getBookViaUUID(String str);
}
