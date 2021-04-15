package pl.lodz.p.it.userinterface.controller;

import pl.lodz.p.it.viewadapters.adapters.MovieServiceAdapter;
import pl.lodz.p.it.viewmodel.modelDTO.MovieDTO;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class AddMovieController implements Serializable {

    @Inject
    private MovieServiceAdapter movieService;
    private MovieDTO movieDTO;

    @PostConstruct
    private void init() {
        movieDTO = new MovieDTO();
    }

    public MovieDTO getMovie() {
        return movieDTO;
    }

    public void addConfirmed() {
        movieService.addMovie(movieDTO);
        init();
    }
}
