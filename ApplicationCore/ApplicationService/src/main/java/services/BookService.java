package services;

import infrastructure.BookPort;
import model.Book;
import usecase.book.BookUsecaseSuit;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@SessionScoped
public class BookService implements Serializable, BookUsecaseSuit {
    @Inject
    private BookPort bookRepo;

    public BookService() {
    }

    public BookService(BookPort bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepo.getAllBooks();
    }

    @Override
    public void updateSingleBook(Book income, Book outcome) {
        bookRepo.updateSingleBook(income, outcome);
    }

    @Override
    public void addBook(Book b) {
        bookRepo.addBook(b);
    }

    public void setBookRented(Book b, boolean value) { bookRepo.setBookRented(b, value); }

    @Override
    public void removeBook(Book b) {
        bookRepo.removeBook(b);
    }

    @Override
    public Book getBookViaUUID(String str) {return bookRepo.getBookViaUUID(str);}

    @Override
    public Book getBook(Book b) {
        return bookRepo.getBook(b);
    }
}
