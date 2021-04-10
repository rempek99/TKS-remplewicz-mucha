package pl.lodz.p.it.userinterface.controller;

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
    private GetAllBookRentalsUsecase getRentalsService;
    @Inject
    private SetBookRentedUsecase setRentedService;
    @Inject
    private RemoveBookRentalUsecase removeRentalService;

    private List<BookRentalDTO> filteredBooks;

    public List<BookRentalDTO> getBookRentals() {return getRentalsService.getAllBookRentals(); }

    public GetAllBookRentalsUsecase getRentalService() {
        return getRentalsService;
    }

    public void setRentalService(GetAllBookRentalsUsecase rentalService) {
        this.getRentalsService = rentalService;
    }

    public List<BookRentalDTO> getFilteredBooks() {
        return filteredBooks;
    }

    public void setFilteredBooks(List<BookRentalDTO> filteredBooks) {
        this.filteredBooks = filteredBooks;
    }

    public void removeSelectedBookRental(BookRentalDTO b) {
        b.getBookDTO().setRented(false);
        setRentedService.setBookRented(b.getBookDTO(), false);
        removeRentalService.removeBookRental(b);
    }
}
