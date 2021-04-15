package pl.lodz.p.it.userinterface.controller;

import pl.lodz.p.it.viewadapters.adapters.RentalServiceAdapter;
import pl.lodz.p.it.viewports.book.SetBookRentedUsecase;
import pl.lodz.p.it.viewmodel.modelDTO.BookRentalDTO;
import pl.lodz.p.it.viewports.rentals.GetAllBookRentalsUsecase;
import pl.lodz.p.it.viewports.rentals.RemoveBookRentalUsecase;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ListBookRentalsController implements Serializable {

    @Inject
    private RentalServiceAdapter rentalService;

    private List<BookRentalDTO> filteredBooks;

    public List<BookRentalDTO> getBookRentals() {return rentalService.getAllBookRentals(); }

//    public GetAllBookRentalsUsecase getRentalService() {
//        return getRentalsService;
//    }

    public void setRentalService(RentalServiceAdapter rentalService) {
        this.rentalService = rentalService;
    }

    public List<BookRentalDTO> getFilteredBooks() {
        return filteredBooks;
    }

    public void setFilteredBooks(List<BookRentalDTO> filteredBooks) {
        this.filteredBooks = filteredBooks;
    }

    public void removeSelectedBookRental(BookRentalDTO b) {
        b.getBook().setRented(false);
        rentalService.setBookRented(b.getBook(), false);
        rentalService.removeBookRental(b);
    }
}
