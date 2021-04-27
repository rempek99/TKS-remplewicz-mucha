package pl.lodz.p.it.userinterface.controller;

import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ListBooksController implements Serializable{

    @Inject
    private BookServiceAdapter bookService;
    private List<BookDTO> filteredBooks;

    public List<BookDTO> getFilteredBooks() {
        return filteredBooks;
    }

    public void setFilteredBooks(List<BookDTO> filteredBooks) {
        this.filteredBooks = filteredBooks;
    }

    public List<BookDTO> getBooks() {
        return bookService.getAllBooks();
    }

    public void removeSelectedBook(BookDTO b) {
        bookService.removeBook(b);
    }

    public void setRentedSelectedBook(BookDTO b, boolean value) {
        bookService.setBookRented(b, value);
    }
}
