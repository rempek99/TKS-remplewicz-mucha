package services;

import infrastructure.MoviePort;
import model.Movie;
import usecase.movie.MovieUsecaseSuit;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

@SessionScoped
public class MovieService implements Serializable, MovieUsecaseSuit {
    @Inject
    private MoviePort movieRepo;

    public MovieService() {
    }


    public MovieService(MoviePort movieRepo) {
        this.movieRepo = movieRepo;
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepo.getAllMovies();
    }

    @Override
    public void addMovie(Movie m) {
        movieRepo.addMovie(m);
    }

    public void setMovieRented(Movie m, boolean value) { movieRepo.setMovieRented(m, value); }

    @Override
    public void removeMovie(Movie m) {
        movieRepo.removeMovie(m);
    }

    @Override
    public Movie getMovieViaUUID(String str) {return movieRepo.getMovieViaUUID(str);}

    @Override
    public void updateSingleMovie(Movie income, Movie outcome) {movieRepo.updateSingleMovie(income, outcome);}
}
