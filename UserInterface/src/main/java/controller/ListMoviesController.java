package controller;

import modelDTO.MovieDTO;
import movie.GetAllMoviesUsecase;
import movie.RemoveMovieUsecase;
import movie.SetMovieRentedUsecase;

import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ListMoviesController implements Serializable {
    @Inject
    private GetAllMoviesUsecase getAllMoviesService;
    @Inject
    private RemoveMovieUsecase removeMovieService;
    @Inject
    private SetMovieRentedUsecase setMovieRentedService;
    private List<MovieDTO> filteredMovies;

    public List<MovieDTO> getFilteredMovies() {
        return filteredMovies;
    }

    public void setFilteredMovies(List<MovieDTO> filteredMovies) {
        this.filteredMovies = filteredMovies;
    }

    public List<MovieDTO> getMovies() {
        return getAllMoviesService.getAllMovies();
    }

    public void printSelectedMovie(MovieDTO m) {
        System.out.println("Selected movie: " + m);
    }

    public void removeSelectedMovie(MovieDTO m) {
        removeMovieService.removeMovie(m);
    }

    public void setRentedSelectedMovie(MovieDTO m, boolean value) { setMovieRentedService.setMovieRented(m, value); }
}