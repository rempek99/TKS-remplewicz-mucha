package pl.lodz.p.it.applicationcore.domainmodel.adapters;

import pl.lodz.p.it.applicationcore.domainmodel.converters.BookConverter;
import pl.lodz.p.it.viewports.book.BookUsecaseSuit;
import pl.lodz.p.it.viewmodel.modelDTO.BookDTO;
import pl.lodz.p.it.applicationcore.applicationservice.services.BookService;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Dependent
public class BookRepoAdapter implements BookUsecaseSuit, Serializable {

    private BookService bookService;

    @Inject
    public BookRepoAdapter(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<BookDTO> bookDTOS = new ArrayList<>();
        bookService.getAll().forEach(r -> bookDTOS.add(getBookViaUUID(r.getId())));
        return bookDTOS;
    }

    @Override
    public void addBook(BookDTO b) {
        bookService.add(BookConverter.convertDTOToBook(b));
    }

    @Override
    public void setBookRented(BookDTO b, boolean value) {
        bookService.setBookRented(BookConverter.convertDTOToBook(b), value);
    }

    @Override
    public void removeBook(BookDTO b) {
        bookService.remove(BookConverter.convertDTOToBook(b));
    }

    @Override
    public BookDTO getBookViaUUID(String str) {
        return BookConverter.convertBookToDTO(bookService.getViaUUID(str));
    }

    @Override
    public BookDTO getBook(BookDTO b) {
        return BookConverter.convertBookToDTO(bookService.get(BookConverter.convertDTOToBook(b)));
    }

    @Override
    public void updateSingleBook(BookDTO bookToChange, BookDTO bookWithData) {
        bookService.update(
                BookConverter.convertDTOToBook(bookToChange),
                BookConverter.convertDTOToBook(bookWithData));
    }
}
