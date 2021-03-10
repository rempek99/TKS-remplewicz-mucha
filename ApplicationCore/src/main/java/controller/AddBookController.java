package controller;

import model.Book;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class AddBookController implements Serializable {

    @Inject
    private BookService bookService;
    private Book book;

    @PostConstruct
    private void init() {
        book = new Book();
    }

    public Book getBook() {
        return book;
    }

    public void addConfirmed() {
        bookService.addBook(book);
        init();
    }
}
