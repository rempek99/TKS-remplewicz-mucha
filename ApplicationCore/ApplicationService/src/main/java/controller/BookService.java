package controller;

import infrastructure.BookPort;
import model.Book;
import usecase.*;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

@Dependent
public class BookService implements Serializable, GetAllBooksUsecase, AddBookUsecase, RemoveBookUsecase,
        GetBookViaUUIDUsecase, UpdateSingleBookUsecase {

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
