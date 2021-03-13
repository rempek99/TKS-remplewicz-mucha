package controller;

import dtoadapters.*;
import dtomodel.*;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class AddMovieController implements Serializable {
    private MovieDTOAdapter movieDTOAdapter;
    private MovieDTO movie;

    @PostConstruct
    private void init() {
        movie = new MovieDTO();
    }

    public MovieDTO getMovie() {
        return movieDTOAdapter.getMovie(movie);
    }

    public void addConfirmed() {
        movieDTOAdapter.addMovie(movie);
        init();
    }
}
