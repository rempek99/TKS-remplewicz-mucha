package controller;

import model.Movie;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class AddMovieController implements Serializable {

    @Inject
    private MovieService movieService;
    private Movie movie;

    @PostConstruct
    private void init() {
        movie = new Movie();
    }

    public Movie getMovie() {
        return movie;
    }

    public void addConfirmed() {
        movieService.addMovie(movie);
        init();
    }
}
