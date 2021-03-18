package aggregates.adapters;

import aggregates.converters.MovieConverter;
import infrastructure.MoviePort;
import model.Movie;
import model_ent.repositories.MovieEntRepo;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static aggregates.converters.MovieConverter.convertMovieToEnt;

@Dependent
public class MovieRepoAdapter implements MoviePort, Serializable {

    private final MovieEntRepo movieRepo;
    private  List<Movie> movies;

    @Inject
    public MovieRepoAdapter(MovieEntRepo movieRepo) {
        this.movieRepo = movieRepo;
        cahceData();
    }

    private void cahceData() {
        movies = movieRepo.getAllMovies()
                .stream()
                .map(MovieConverter::convertEntToMovie)
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> temp = Collections.synchronizedList(new ArrayList<Movie>());
        cahceData();
        return movies;
    }


    @Override
    public void addMovie(Movie m) {
        movieRepo.addMovie(convertMovieToEnt(m));
    }


    @Override
    public void setMovieRented(Movie m, boolean value) {
        movieRepo.setMovieRented(convertMovieToEnt(m), value);
    }

    @Override
    public void removeMovie(Movie m) {
        movieRepo.removeMovie(convertMovieToEnt(m));
    }


    @Override
    public Movie getMovieViaUUID(String str) {
        cahceData();
        return movies.stream()
                .filter(x -> x.getId().equals(str))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Movie getMovie(Movie m) {
        cahceData();
        if (movies.contains(m)) {
            return m;
        } else {
            return null;
        }
    }


    @Override
    public void updateSingleMovie(Movie movieToChange, Movie movieWithData) {
            movieRepo.updateSingleMovie(
                    convertMovieToEnt(movieToChange),
                    convertMovieToEnt(movieWithData));
    }
}
