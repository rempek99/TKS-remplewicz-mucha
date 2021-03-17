package controller;

import model.BookRental;
import services.BookService;
import services.RentalService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ListBookRentalsController implements Serializable {
    @Inject
    private RentalService rentalService;
    @Inject
    private BookService bookService;

    private List<BookRental> filteredBooks;

    public List<BookRental> getBookRentals() {return rentalService.getAllBookRentals(); }

    public RentalService getRentalService() {
        return rentalService;
    }

    public void setRentalService(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    public List<BookRental> getFilteredBooks() {
        return filteredBooks;
    }

    public void setFilteredBooks(List<BookRental> filteredBooks) {
        this.filteredBooks = filteredBooks;
    }

    public void removeSelectedBookRental(BookRental b) {
        b.getBook().setRented(false);
        bookService.setBookRented(b.getBook(), false);
        rentalService.removeBookRental(b);
    }
}
