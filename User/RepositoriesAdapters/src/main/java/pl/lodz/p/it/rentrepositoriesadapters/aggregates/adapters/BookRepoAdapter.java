package pl.lodz.p.it.rentrepositoriesadapters.aggregates.adapters;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Book;
import pl.lodz.p.it.rentrepositoriesadapters.model_ent.repositories.BookEntRepo;
import pl.lodz.p.it.rentrepositoriesadapters.aggregates.converters.BookConverter;
import pl.lodz.p.it.applicationports.infrastructure.BookPort;
import pl.lodz.p.it.rentrepositoriesadapters.model_ent.repositories.RepositoryException;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static pl.lodz.p.it.rentrepositoriesadapters.aggregates.converters.BookConverter.convertBookToEnt;
import static pl.lodz.p.it.rentrepositoriesadapters.aggregates.converters.BookConverter.convertEntToBook;

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
        books = bookRepo.getAll()
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
    public Book addBook(Book b) {
        try {
            return convertEntToBook(bookRepo.add(convertBookToEnt(b)));
        } catch (RepositoryException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void setBookRented(Book b, boolean value) { bookRepo.setBookRented(convertBookToEnt(b), value); }

    @Override
    public void removeBook(Book b) {
        bookRepo.remove(convertBookToEnt(b));
    }

    @Override
    public Optional<Book> getBookViaUUID(String str) {
        cacheData();
        return books.stream()
                .filter(x -> x.getId().equals(str))
                .findAny();
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
        bookRepo.update(
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
