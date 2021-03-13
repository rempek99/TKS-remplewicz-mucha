package controller;

import dtoadapters.BookDTOAdapter;
import dtomodel.*;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class AddBookController implements Serializable {
    private BookDTOAdapter bookDTOAdapter;
    private BookDTO book;

    @PostConstruct
    private void init() {
        book = new BookDTO();
    }

    public BookDTO getBook() {
        return bookDTOAdapter.getBook(book);
    }

    public void addConfirmed() {
        bookDTOAdapter.addBook(book);
        init();
    }
}
