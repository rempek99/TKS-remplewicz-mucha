package controller;

import dtoadapters.*;
import dtomodel.*;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ListMovieRentalsController implements Serializable {
    private RentalDTOAdapter rentalDTOAdapter;
    private List<MovieRentalDTO> filteredMovies;

    public List<MovieRentalDTO> getMovieRentals() {return rentalDTOAdapter.getMovieRentals(); }

    public RentalDTOAdapter getRentalService() {
        return rentalDTOAdapter;
    }

    public void setRentalService(RentalDTOAdapter rentalDTOAdapter) {
        this.rentalDTOAdapter = rentalDTOAdapter;
    }

    public List<MovieRentalDTO> getFilteredMovies() {
        return filteredMovies;
    }

    public void setFilteredMovies(List<MovieRentalDTO> filteredMovies) {
        this.filteredMovies = filteredMovies;
    }

    public void removeSelectedMovieRental(MovieRentalDTO m) {
        m.getMovie().setRented(false);
        rentalDTOAdapter.removeMovieRental(m);
    }
}
