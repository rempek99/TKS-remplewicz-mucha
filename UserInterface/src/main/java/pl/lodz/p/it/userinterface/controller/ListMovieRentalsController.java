package pl.lodz.p.it.userinterface.controller;

import pl.lodz.p.it.viewmodel.modelDTO.MovieRentalDTO;
import pl.lodz.p.it.viewports.movie.SetMovieRentedUsecase;
import pl.lodz.p.it.viewports.rentals.GetAllMovieRentalsUsecase;
import pl.lodz.p.it.viewports.rentals.RemoveMovieRentalUsecase;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ListMovieRentalsController implements Serializable {
    @Inject
    private GetAllMovieRentalsUsecase getAllMovieRentalsService;
    @Inject
    private RemoveMovieRentalUsecase removeMovieRentalService;
    @Inject
    private SetMovieRentedUsecase movieService;

    private List<MovieRentalDTO> filteredMovies;

    public List<MovieRentalDTO> getMovieRentals() {return getAllMovieRentalsService.getAllMovieRentals(); }

    public GetAllMovieRentalsUsecase getRentalService() {
        return getAllMovieRentalsService;
    }

    public void setRentalService(GetAllMovieRentalsUsecase rentalService) {
        this.getAllMovieRentalsService = rentalService;
    }

    public List<MovieRentalDTO> getFilteredMovies() {
        return filteredMovies;
    }

    public void setFilteredMovies(List<MovieRentalDTO> filteredMovies) {
        this.filteredMovies = filteredMovies;
    }

    public void removeSelectedMovieRental(MovieRentalDTO m) {
        m.getMovie().setRented(false);
        movieService.setMovieRented(m.getMovie(), false);
        removeMovieRentalService.removeMovieRental(m);
    }
}
