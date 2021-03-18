package controller;

import model.Book;
import services.BookService;

import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ListBooksController implements Serializable{
    @Inject
    private BookService bookService;
    private List<Book> filteredBooks;

    public List<Book> getFilteredBooks() {
        return filteredBooks;
    }

    public void setFilteredBooks(List<Book> filteredBooks) {
        this.filteredBooks = filteredBooks;
    }

    public List<Book> getBooks() {
        return bookService.getAll();
    }

    public void removeSelectedBook(Book b) {
        bookService.remove(b);
    }

    public void setRentedSelectedBook(Book b, boolean value) {
        bookService.setBookRented(b, value);
    }
}
