package adapters;

import converters.BookConverter;
import book.BookUsecaseSuit;
import modelDTO.BookDTO;
import services.BookService;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Dependent
public class BookRepoAdapter implements BookUsecaseSuit, Serializable {

    private BookService bookService;
    private BookConverter bookConverter;

    @Inject
    public BookRepoAdapter(BookService bookService) {
        this.bookService = bookService;
        bookConverter = new BookConverter();
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<BookDTO> bookDTOS = new ArrayList<>();
        bookService.getAll().forEach(r -> bookDTOS.add(getBookViaUUID(r.getId())));
        return bookDTOS;
    }

    @Override
    public void addBook(BookDTO b) {
        bookService.add(bookConverter.convertDTOToBook(b));
    }

    @Override
    public void setBookRented(BookDTO b, boolean value) {
        bookService.setBookRented(bookConverter.convertDTOToBook(b), value);
    }

    @Override
    public void removeBook(BookDTO b) {
        bookService.remove(bookConverter.convertDTOToBook(b));
    }

    @Override
    public BookDTO getBookViaUUID(String str) {
        return bookConverter.convertBookToDTO(bookService.getViaUUID(str));
    }

    @Override
    public BookDTO getBook(BookDTO b) {
        return bookConverter.convertBookToDTO(bookService.get(bookConverter.convertDTOToBook(b)));
    }

    @Override
    public void updateSingleBook(BookDTO bookToChange, BookDTO bookWithData) {
        bookService.update(bookConverter.convertDTOToBook(bookToChange), bookConverter.convertDTOToBook(bookWithData));
//        Book fromRepo = getBook(bookToChange);
//        fromRepo.setTitle(bookWithData.getTitle());
//        fromRepo.setAuthor(bookWithData.getAuthor());
//        fromRepo.setPages(bookWithData.getPages());
//        fromRepo.setRented(bookWithData.isRented());
    }
}
