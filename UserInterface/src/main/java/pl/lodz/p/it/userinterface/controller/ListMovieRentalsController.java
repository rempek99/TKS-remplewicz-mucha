package pl.lodz.p.it.userinterface.controller;

import pl.lodz.p.it.rentviewports.rentals.GetAllMovieRentalsUsecase;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ListMovieRentalsController implements Serializable {

    @Inject
    private RentalServiceAdapter rentalService;

    private List<MovieRentalDTO> filteredMovies;

    public List<MovieRentalDTO> getMovieRentals() {return rentalService.getAllMovieRentals(); }

    public GetAllMovieRentalsUsecase getRentalService() {
        return rentalService;
    }

    public void setRentalService(RentalServiceAdapter rentalService) {
        this.rentalService = rentalService;
    }

    public List<MovieRentalDTO> getFilteredMovies() {
        return filteredMovies;
    }

    public void setFilteredMovies(List<MovieRentalDTO> filteredMovies) {
        this.filteredMovies = filteredMovies;
    }

    public void removeSelectedMovieRental(MovieRentalDTO m) {
        m.getMovie().setRented(false);
        rentalService.setMovieRented(m.getMovie(), false);
        rentalService.removeMovieRental(m);
    }
}
