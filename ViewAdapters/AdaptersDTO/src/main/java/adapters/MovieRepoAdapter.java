package adapters;

import converters.MovieConverter;
import model.Movie;
import modelDTO.MovieDTO;
import model_ent.repositories.MovieEntRepo;
import movie.MovieUsecaseSuit;
import repositoriesDTO.MovieRepoDTO;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static converters.MovieConverter.convertMovieToDTO;
import static converters.MovieConverter.convertDTOToMovie;

@Dependent
public class MovieRepoAdapter implements MovieUsecaseSuit, Serializable {

    private final MovieRepoDTO movieRepo;
    private  List<Movie> movies;

    @Inject
    public MovieRepoAdapter(MovieRepoDTO movieRepo) {
        this.movieRepo = movieRepo;
        cahceData();
    }

    private void cahceData() {
        movies = movieRepo.getAll()
                .stream()
                .map(MovieConverter::convertDTOToMovie)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieDTO> getAllMovies() {
        List<MovieDTO> temp = Collections.synchronizedList(new ArrayList<MovieDTO>());
        cahceData();
        for (Movie movie: movies) {
            temp.add(convertMovieToDTO(movie));
        }
        return temp;
    }


    @Override
    public void addMovie(MovieDTO m) {
        movieRepo.add(convertMovieToDTO(m));
    }


    @Override
    public void setMovieRented(MovieDTO m, boolean value) {
        movieRepo.setMovieRented(convertMovieToEnt(m), value);
    }

    @Override
    public void removeMovie(MovieDTO m) {
        movieRepo.remove(convertMovieToDTO(m));
    }


    @Override
    public MovieDTO getMovieViaUUID(String str) {
        cahceData();
        return movies.stream()
                .filter(x -> x.getId().equals(str))
                .findFirst()
                .orElse(null);
    }

    @Override
    public MovieDTO getMovie(MovieDTO m) {
        cahceData();
        if (movies.contains(m)) {
            return m;
        } else {
            return null;
        }
    }


    @Override
    public void updateSingleMovie(MovieDTO movieToChange, MovieDTO movieWithData) {
            movieRepo.update(
                    convertMovieToDTO(movieToChange),
                    convertMovieToDTO(movieWithData));
    }
}
