package pl.lodz.p.it.soap.api;


import pl.lodz.p.it.repositoriesadapters.model_ent.repositories.RepositoryException;
import pl.lodz.p.it.soap.aggregates.adapters.BookSoapAdapter;
import pl.lodz.p.it.soap.model.BookSoap;
import pl.lodz.p.it.soap.model.SoapException;
import pl.lodz.p.it.soap.model.SoapMessage;

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

    public BookSoap addSingleBookToStorage(BookSoap book) throws SoapException {
        try {
            return bookAdapter.addBook(book);
        } catch (IllegalArgumentException e) {
            if (e.getCause().getMessage().equals(RepositoryException.DUPLICATED))
                throw new SoapException(SoapException.DUPLICATED);
            else
                throw e;
        }
    }

    public String removeSingleBookFromStorage(String str) throws SoapException {
        Optional<BookSoap> book = Optional.ofNullable(bookAdapter.getBookViaUUID(str));
        if (book.isPresent()) {
            bookAdapter.removeBook(book.get());
            return SoapMessage.OK;
        } else {
            throw new SoapException(SoapException.NOT_FOUND);
        }
    }

    public BookSoap getSingleBookFromStorage(String str) throws SoapException {
        try {
            return bookAdapter.getBookViaUUID(str);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals(SoapException.NOT_FOUND))
                throw new SoapException(SoapException.NOT_FOUND);
            else
                throw e;
        }
    }

    public String updateSingleBook(String str, BookSoap desiredBook) throws SoapException {
        Optional<BookSoap> bookToChange = Optional.ofNullable(bookAdapter.getBookViaUUID(str));
        if (bookToChange.isPresent()) {
            bookAdapter.updateSingleBook(bookToChange.get(), desiredBook);
            return SoapMessage.OK;
        } else {
            throw new SoapException(SoapException.NOT_FOUND);
        }
    }
}
