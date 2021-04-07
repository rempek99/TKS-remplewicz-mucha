package controller;

import modelDTO.MovieDTO;
import movie.AddMovieUsecase;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class AddMovieController implements Serializable {

    @Inject
    private AddMovieUsecase addMovieService;
    private MovieDTO movieDTO;

    @PostConstruct
    private void init() {
        movieDTO = new MovieDTO();
    }

    public MovieDTO getMovie() {
        return movieDTO;
    }

    public void addConfirmed() {
        addMovieService.addMovie(movieDTO);
        init();
    }
}
