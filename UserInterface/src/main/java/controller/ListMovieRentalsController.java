package controller;

import model.MovieRental;
import services.MovieService;
import services.RentalService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ListMovieRentalsController implements Serializable {
    @Inject
    private RentalService rentalService;
    @Inject
    private MovieService movieService;

    private List<MovieRental> filteredMovies;

    public List<MovieRental> getMovieRentals() {return rentalService.getAllMovieRentals(); }

    public RentalService getRentalService() {
        return rentalService;
    }

    public void setRentalService(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    public List<MovieRental> getFilteredMovies() {
        return filteredMovies;
    }

    public void setFilteredMovies(List<MovieRental> filteredMovies) {
        this.filteredMovies = filteredMovies;
    }

    public void removeSelectedMovieRental(MovieRental m) {
        m.getMovie().setRented(false);
        movieService.setMovieRented(m.getMovie(), false);
        rentalService.removeMovieRental(m);
    }
}
