package adapters;

import converters.BookConverter;
import book.BookUsecaseSuit;
import infrastructure.BookPort;
import model.Book;
import modelDTO.BookDTO;
import model_ent.repositories.BookEntRepo;
import repositoriesDTO.BookRepoDTO;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static converters.BookConverter.convertBookToDTO;
import static converters.BookConverter.convertDTOToBook;

@Dependent
public class BookRepoAdapter implements BookUsecaseSuit, Serializable {

    private final BookRepoDTO bookRepo;

    private List<Book> books;

    @Inject
    public BookRepoAdapter(BookRepoDTO bookRepo) {
        this.bookRepo = bookRepo;
        cacheData();
    }

    private void cacheData() {
        books = bookRepo.getAll()
                .stream()
                .map(BookConverter::convertDTOToBook)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> getAllBooks() {
        cacheData();
        return bookRepo.getAll();
    }

    @Override
    public void addBook(BookDTO b) {
        bookRepo.add(convertDTOToBook(b));
    }

    @Override
    public void setBookRented(BookDTO b, boolean value) { bookRepo.setBookRented(convertDTOToBook(b), value); }

    @Override
    public void removeBook(BookDTO b) {
        bookRepo.remove(convertBookToDTO(b));
    }

    @Override
    public BookDTO getBookViaUUID(String str) {
        cacheData();
        return books.stream()
                .filter(x -> x.getId().equals(str))
                .findAny()
                .orElse(null);
    }

    @Override
    public BookDTO getBook(BookDTO b) {
        cacheData();
        if (books.contains(b)) {
            return b;
        } else {
            return null;
        }
    }

    @Override
    public void updateSingleBook(BookDTO bookToChange, BookDTO bookWithData) {
        bookRepo.update(
                convertBookToDTO(bookToChange),
                convertBookToDTO(bookWithData)
        );
//        Book fromRepo = getBook(bookToChange);
//        fromRepo.setTitle(bookWithData.getTitle());
//        fromRepo.setAuthor(bookWithData.getAuthor());
//        fromRepo.setPages(bookWithData.getPages());
//        fromRepo.setRented(bookWithData.isRented());
    }
}
