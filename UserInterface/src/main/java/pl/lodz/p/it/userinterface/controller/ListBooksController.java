package pl.lodz.p.it.userinterface.controller;

import pl.lodz.p.it.viewports.book.GetAllBooksUsecase;
import pl.lodz.p.it.viewports.book.RemoveBookUsecase;
import pl.lodz.p.it.viewports.book.SetBookRentedUsecase;
import pl.lodz.p.it.viewmodel.modelDTO.BookDTO;

import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ListBooksController implements Serializable{
    @Inject
    private GetAllBooksUsecase getBooksService;
    @Inject
    private RemoveBookUsecase removeBookService;
    @Inject
    private SetBookRentedUsecase setBookRentedService;
    private List<BookDTO> filteredBooks;

    public List<BookDTO> getFilteredBooks() {
        return filteredBooks;
    }

    public void setFilteredBooks(List<BookDTO> filteredBooks) {
        this.filteredBooks = filteredBooks;
    }

    public List<BookDTO> getBooks() {
        return getBooksService.getAllBooks();
    }

    public void removeSelectedBook(BookDTO b) {
        removeBookService.removeBook(b);
    }

    public void setRentedSelectedBook(BookDTO b, boolean value) {
        setBookRentedService.setBookRented(b, value);
    }
}
