package aggregates.adapters;

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

import static aggregates.converters.BookConverter.convertBookToEnt;
import static aggregates.converters.BookConverter.convertEntToBook;

@Dependent
public class BookRepoAdapter implements BookPort, Serializable {

    private final BookEntRepo bookRepo;

    private List<BookEnt> books;

    @Inject
    public BookRepoAdapter(BookEntRepo bookRepo) {
        this.bookRepo = bookRepo;
        books = bookRepo.getAllBooks();
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> temp = Collections.synchronizedList(new ArrayList<Book>());
        for (BookEnt book: books) {
            temp.add(convertEntToBook(book));
        }
        return temp;
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
        for(BookEnt book: books) {
            if(book.getId().equals(str)){
                return convertEntToBook(book);
            }
        }
        return null;
    }

    @Override
    public Book getBook(Book b) {
        if (books.contains(b)) {
            return b;
        } else {
            return null;
        }
    }

    @Override
    public void updateSingleBook(Book bookToChange, Book bookWithData) {
        Book fromRepo = getBook(bookToChange);
        fromRepo.setTitle(bookWithData.getTitle());
        fromRepo.setAuthor(bookWithData.getAuthor());
        fromRepo.setPages(bookWithData.getPages());
        fromRepo.setRented(bookWithData.isRented());
    }
}
