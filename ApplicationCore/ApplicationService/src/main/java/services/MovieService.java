package services;

import infrastructure.MoviePort;
import model.Movie;
import usecase.movie.MovieUsecaseSuit;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

@SessionScoped
public class MovieService implements Serializable, MovieUsecaseSuit, IService<Movie> {
    @Inject
    private MoviePort movieRepo;

    public MovieService() {
    }


    public MovieService(MoviePort movieRepo) {
        this.movieRepo = movieRepo;
    }

    @Override
    public List<Movie> getAll() {
        return movieRepo.getAllMovies();
    }

    @Override
    public void add(Movie m) {
        movieRepo.addMovie(m);
    }

    public void setMovieRented(Movie m, boolean value) { movieRepo.setMovieRented(m, value); }

    @Override
    public Movie get(Movie m) { return movieRepo.getMovie(m); }

    @Override
    public void remove(Movie m) { movieRepo.removeMovie(m); }

    @Override
    public Movie getViaUUID(String str) {return movieRepo.getMovieViaUUID(str);}

    @Override
    public void update(Movie income, Movie outcome) {movieRepo.updateSingleMovie(income, outcome);}
}
