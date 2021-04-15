package pl.lodz.p.it.userinterface.controller;

import pl.lodz.p.it.viewadapters.adapters.MovieServiceAdapter;
import pl.lodz.p.it.viewmodel.modelDTO.MovieDTO;
import pl.lodz.p.it.viewports.movie.GetAllMoviesUsecase;
import pl.lodz.p.it.viewports.movie.RemoveMovieUsecase;
import pl.lodz.p.it.viewports.movie.SetMovieRentedUsecase;

import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ListMoviesController implements Serializable {

    @Inject
    private MovieServiceAdapter movieService;
    private List<MovieDTO> filteredMovies;

    public List<MovieDTO> getFilteredMovies() {
        return filteredMovies;
    }

    public void setFilteredMovies(List<MovieDTO> filteredMovies) {
        this.filteredMovies = filteredMovies;
    }

    public List<MovieDTO> getMovies() {
        return movieService.getAllMovies();
    }

    public void printSelectedMovie(MovieDTO m) {
        System.out.println("Selected pl.lodz.p.it.viewports.movie: " + m);
    }

    public void removeSelectedMovie(MovieDTO m) {
        movieService.removeMovie(m);
    }

    public void setRentedSelectedMovie(MovieDTO m, boolean value) { movieService.setMovieRented(m, value); }
}