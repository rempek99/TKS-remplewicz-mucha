package aggregates.adapters;

import aggregates.converters.BookConverter;
import infrastructure.BookPort;
import model.Book;
import model_ent.entities.BookEnt;
import model_ent.repositories.BookEntRepo;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static aggregates.converters.BookConverter.convertBookToEnt;
import static aggregates.converters.BookConverter.convertEntToBook;

@Dependent
public class BookRepoAdapter implements BookPort, Serializable {

    private final BookEntRepo bookRepo;

    private List<Book> books;

    @Inject
    public BookRepoAdapter(BookEntRepo bookRepo) {
        this.bookRepo = bookRepo;
        cacheData();
    }

    private void cacheData() {
        books = bookRepo.getAllBooks()
                .stream()
                .map(BookConverter::convertEntToBook)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> getAllBooks() {
        cacheData();
        return books;
    }

    @Override
    public void addBook(Book b) {
        bookRepo.addBook(convertBookToEnt(b));
    }

    @Override
    public void setBookRented(Book b, boolean value) { bookRepo.setBookRented(convertBookToEnt(b), value); }

    @Override
    public void removeBook(Book b) {
        bookRepo.removeBook(convertBookToEnt(b));
    }

    @Override
    public Book getBookViaUUID(String str) {
        cacheData();
        return books.stream()
                .filter(x -> x.getId().equals(str))
                .findAny()
                .orElse(null);
    }

    @Override
    public Book getBook(Book b) {
        cacheData();
        if (books.contains(b)) {
            return b;
        } else {
            return null;
        }
    }

    @Override
    public void updateSingleBook(Book bookToChange, Book bookWithData) {
        bookRepo.updateSingleBook(
                convertBookToEnt(bookToChange),
                convertBookToEnt(bookWithData)
        );
//        Book fromRepo = getBook(bookToChange);
//        fromRepo.setTitle(bookWithData.getTitle());
//        fromRepo.setAuthor(bookWithData.getAuthor());
//        fromRepo.setPages(bookWithData.getPages());
//        fromRepo.setRented(bookWithData.isRented());
    }
}
