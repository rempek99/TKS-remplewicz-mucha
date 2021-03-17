package services;

import infrastructure.MoviePort;
import model.Movie;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

@SessionScoped
public class MovieService implements Serializable{

    private MoviePort movieRepo;

    @Inject
    public MovieService(MoviePort movieRepo) {
        this.movieRepo = movieRepo;
    }

    public List<Movie> getAllMovies() {
        return movieRepo.getAllMovies();
    }

    public void addMovie(Movie m) {
        movieRepo.addMovie(m);
    }

    public void setMovieRented(Movie m, boolean value) { movieRepo.setMovieRented(m, value); }

    public void removeMovie(Movie m) {
        movieRepo.removeMovie(m);
    }

    public Movie getMovieViaUUID(String str) {return movieRepo.getMovieViaUUID(str);}

    public void updateSingleMovie(Movie income, Movie outcome) {movieRepo.updateSingleMovie(income, outcome);}
}
