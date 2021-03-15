package services;

import infrastructure.BookPort;
import model.Book;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@SessionScoped
public class BookService implements Serializable{
    @Inject
    private BookPort bookRepo;

    public List<Book> getAllBooks() {
        return bookRepo.getAllBooks();
    }

    public void updateSingleBook(Book income, Book outcome) {
        bookRepo.updateSingleBook(income, outcome);
    }
    public void addBook(Book b) {
        bookRepo.addBook(b);
    }

    public void removeBook(Book b) {
        bookRepo.removeBook(b);
    }

    public Book getBookViaUUID(String str) {return bookRepo.getBookViaUUID(str);}
}
