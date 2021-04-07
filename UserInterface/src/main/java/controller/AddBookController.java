package controller;

import book.AddBookUsecase;
import modelDTO.BookDTO;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class AddBookController implements Serializable {

    @Inject
    private AddBookUsecase addBookService;
    private BookDTO bookDTO;

    @PostConstruct
    private void init() {
        bookDTO = new BookDTO();
    }

    public BookDTO getBook() {
        return bookDTO;
    }

    public void addConfirmed() {
        addBookService.addBook(bookDTO);
        init();
    }
}
