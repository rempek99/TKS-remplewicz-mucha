package pl.lodz.p.it.soap.api;


import pl.lodz.p.it.soap.aggregates.adapters.BookSoapAdapter;
import pl.lodz.p.it.soap.model.BookSoap;

import javax.inject.Inject;
import javax.jws.WebService;
import java.util.List;
import java.util.Optional;

@WebService(serviceName = "BookAPI")
public class BookSoapAPI {

    @Inject
    BookSoapAdapter bookAdapter;

    public List<BookSoap> getBooksFromStorage() {
        return bookAdapter.getAllBooks();
    }

    public void addSingleBookToStorage(BookSoap book) {
        bookAdapter.addBook(book);
    }

    public void removeSingleBookFromStorage(String str) {
        Optional<BookSoap> book = Optional.ofNullable(bookAdapter.getBookViaUUID(str));
        if(book.isPresent()){
            bookAdapter.removeBook(book.get());
        }
        else{
            throw new IllegalArgumentException("Book not found");
        }
    }

    public void updateSingleBook(String str, BookSoap desiredBook) {
        Optional<BookSoap> bookToChange = Optional.ofNullable(bookAdapter.getBookViaUUID(str));
        if (bookToChange.isPresent()) {
            bookAdapter.updateSingleBook(bookToChange.get(), desiredBook);
        } else {
            throw new IllegalArgumentException("Book not found");
        }
    }
}
