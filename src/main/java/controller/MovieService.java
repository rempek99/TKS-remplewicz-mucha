package controller;

import model.Movie;
import model.MovieRepo;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

@SessionScoped
public class MovieService implements Serializable{
    @Inject
    private MovieRepo movieRepo;

    public List<Movie> getAllMovies() {
        return movieRepo.getAllMovies();
    }

    public void addMovie(Movie m) {
        movieRepo.addMovie(m);
    }

    public void removeMovie(Movie m) {
        movieRepo.removeMovie(m);
    }

    public Movie getMovieViaUUID(String str) {return movieRepo.getMovieViaUUID(str);}

    public void updateSingleMovie(Movie income, Movie outcome) {movieRepo.updateSingleMovie(income, outcome);}
}
