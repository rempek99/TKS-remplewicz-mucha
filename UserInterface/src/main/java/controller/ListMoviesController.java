package controller;

import dtoadapters.*;
import dtomodel.*;

import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ListMoviesController implements Serializable {
    private MovieDTOAdapter movieDTOAdapter;
    private List<MovieDTO> filteredMovies;

    public List<MovieDTO> getFilteredMovies() {
        return filteredMovies;
    }

    public void setFilteredMovies(List<MovieDTO> filteredMovies) {
        this.filteredMovies = filteredMovies;
    }

    public List<MovieDTO> getMovies() {
        return movieDTOAdapter.getAllMovies();
    }

    public void printSelectedMovie(MovieDTO m) {
        System.out.println("Selected movie: " + m);
    }

    public void removeSelectedMovie(MovieDTO m) {
        movieDTOAdapter.removeMovie(m);
    }
}