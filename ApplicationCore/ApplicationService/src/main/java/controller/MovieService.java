package controller;

import infrastructure.MoviePort;
import model.Movie;
import usecase.*;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Dependent
public class MovieService implements Serializable, GetAllMoviesUsecase, AddMovieUsecase, RemoveMovieUsecase,
        GetMovieViaUUIDUsecase, UpdateSingleMovieUsecase {

    private MoviePort movieRepo;

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
