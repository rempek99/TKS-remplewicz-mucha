package controller;

import model.Movie;
import services.MovieService;

import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ListMoviesController implements Serializable {
    @Inject
    private MovieService movieService;
    private List<Movie> filteredMovies;

    public List<Movie> getFilteredMovies() {
        return filteredMovies;
    }

    public void setFilteredMovies(List<Movie> filteredMovies) {
        this.filteredMovies = filteredMovies;
    }

    public List<Movie> getMovies() {
        return movieService.getAllMovies();
    }

    public void printSelectedMovie(Movie m) {
        System.out.println("Selected movie: " + m);
    }

    public void removeSelectedMovie(Movie m) {
        movieService.removeMovie(m);
    }
}