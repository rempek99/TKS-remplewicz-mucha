package pl.lodz.p.it.soap.aggregates.adapters;

import pl.lodz.p.it.applicationports.usecase.book.BookUsecaseSuit;
import pl.lodz.p.it.soap.aggregates.converters.BookSoapConverter;
import pl.lodz.p.it.soap.model.SoapException;
import pl.lodz.p.it.soap.model.BookSoap;
import pl.lodz.p.it.viewports.book.BookViewPortUsecaseSuit;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Dependent
public class BookSoapAdapter implements BookViewPortUsecaseSuit<BookSoap>, Serializable {

    private BookUsecaseSuit bookService;

    @Inject
    public BookSoapAdapter(BookUsecaseSuit bookService) {
        this.bookService = bookService;
    }

    @Override
    public BookSoap addBook(BookSoap bookSoap) {
        return BookSoapConverter.convertBookToBookSoap(
                bookService.add(BookSoapConverter.convertBookSoapToBook(bookSoap))
        );
    }

    @Override
    public List<BookSoap> getAllBooks() {
        return bookService
                .getAll()
                .stream()
                .map(BookSoapConverter::convertBookToBookSoap)
                .collect(Collectors.toList());
    }

    @Override
    public BookSoap getBook(BookSoap bookSoap) {
        return BookSoapConverter.convertBookToBookSoap(bookService.get(BookSoapConverter.convertBookSoapToBook(bookSoap)));
    }

    @Override
    public BookSoap getBookViaUUID(String str) {
        if(bookService.getViaUUID(str).isPresent()) {
            return BookSoapConverter.convertBookToBookSoap(
                    bookService.getViaUUID(str).get());
        }
        else
            throw new IllegalArgumentException(SoapException.NOT_FOUND);
    }

    @Override
    public void removeBook(BookSoap bookSoap) {
        bookService.remove(BookSoapConverter.convertBookSoapToBook(bookSoap));
    }

    @Override
    public void setBookRented(BookSoap bookSoap, boolean value) {
        bookService.setBookRented(BookSoapConverter.convertBookSoapToBook(bookSoap), value);
    }

    @Override
    public void updateSingleBook(BookSoap income, BookSoap outcome) {
        bookService.update(
                BookSoapConverter.convertBookSoapToBook(income),
                BookSoapConverter.convertBookSoapToBook(outcome));
    }

}
