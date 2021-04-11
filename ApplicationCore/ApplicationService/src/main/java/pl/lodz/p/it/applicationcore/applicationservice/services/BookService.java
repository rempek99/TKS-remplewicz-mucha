package pl.lodz.p.it.applicationcore.applicationservice.services;

import pl.lodz.p.it.applicationports.infrastructure.BookPort;
import pl.lodz.p.it.applicationcore.domainmodel.model.Book;
import pl.lodz.p.it.applicationports.usecase.book.BookUsecaseSuit;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@SessionScoped
public class BookService implements Serializable, BookUsecaseSuit, IService<Book> {
    @Inject
    private BookPort bookRepo;

    public BookService() {
    }

    public BookService(BookPort bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public List<Book> getAll() {
        return bookRepo.getAllBooks();
    }

    @Override
    public void update(Book income, Book outcome) {
        bookRepo.updateSingleBook(income, outcome);
    }

    @Override
    public void add(Book b) {
        bookRepo.addBook(b);
    }

    @Override
    public void setBookRented(Book b, boolean value) { bookRepo.setBookRented(b, value); }

    @Override
    public void remove(Book b) {
        bookRepo.removeBook(b);
    }

    @Override
    public Book getViaUUID(String str) {return bookRepo.getBookViaUUID(str);}

    @Override
    public Book get(Book b) {
        return bookRepo.getBook(b);
    }
}
