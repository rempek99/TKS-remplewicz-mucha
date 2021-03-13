package controller;

import dtoadapters.*;
import dtomodel.*;
import usecase.*;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ListBookRentalsController implements Serializable {
    private RentalDTOAdapter rentalDTOAdapter;
//    @Inject
//    private GetAllBookRentalsUsecase getAllBookRentalsUsecase;
//
//    @Inject
//    private RemoveBookRentalUsecase removeBookRentalUsecase;

    private List<BookRentalDTO> filteredBooks;

    public List<BookRentalDTO> getBookRentals() {return rentalDTOAdapter.getBookRentals(); }

    public RentalDTOAdapter getRentalService() {
        return rentalDTOAdapter;
    }

    public void setRentalService(RentalDTOAdapter rentalDTOAdapter) {
        this.rentalDTOAdapter = rentalDTOAdapter;
    }

    public List<BookRentalDTO> getFilteredBooks() {
        return filteredBooks;
    }

    public void setFilteredBooks(List<BookRentalDTO> filteredBooks) {
        this.filteredBooks = filteredBooks;
    }

    public void removeSelectedBookRental(BookRentalDTO m) {
        m.getBook().setRented(false);
        rentalDTOAdapter.removeBookRental(m);
    }
}
