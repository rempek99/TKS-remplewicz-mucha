package pl.lodz.p.it.viewadapters.adapters;

import pl.lodz.p.it.applicationcore.applicationservice.services.BookService;
import pl.lodz.p.it.applicationports.usecase.book.BookUsecaseSuit;
import pl.lodz.p.it.viewadapters.converters.BookConverter;
import pl.lodz.p.it.viewmodel.modelDTO.BookDTO;
import pl.lodz.p.it.viewports.book.BookViewPortUsecaseSuit;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Dependent
public class BookServiceAdapter implements BookViewPortUsecaseSuit, Serializable {

    private BookUsecaseSuit bookService;

    @Inject
    public BookServiceAdapter(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookService
                .getAll()
                .stream()
                .map(BookConverter::convertBookToDTO)
                .collect(Collectors.toList());
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
