package pl.lodz.p.it.repositoriesadapters.aggregates.adapters;

import pl.lodz.p.it.repositoriesadapters.model_ent.repositories.MovieEntRepo;
import pl.lodz.p.it.repositoriesadapters.aggregates.converters.MovieConverter;
import pl.lodz.p.it.applicationports.infrastructure.MoviePort;
import pl.lodz.p.it.applicationcore.domainmodel.model.Movie;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static pl.lodz.p.it.repositoriesadapters.aggregates.converters.MovieConverter.convertEntToMovie;
import static pl.lodz.p.it.repositoriesadapters.aggregates.converters.MovieConverter.convertMovieToEnt;

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
        movies = movieRepo.getAll()
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
    public Movie addMovie(Movie m) {
        return convertEntToMovie(movieRepo.add(convertMovieToEnt(m)));
    }


    @Override
    public void setMovieRented(Movie m, boolean value) {
        movieRepo.setMovieRented(convertMovieToEnt(m), value);
    }

    @Override
    public void removeMovie(Movie m) {
        movieRepo.remove(convertMovieToEnt(m));
    }


    @Override
    public Optional<Movie> getMovieViaUUID(String str) {
        cahceData();
        return movies.stream()
                .filter(x -> x.getId().equals(str))
                .findFirst();
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
            movieRepo.update(
                    convertMovieToEnt(movieToChange),
                    convertMovieToEnt(movieWithData));
    }
}
