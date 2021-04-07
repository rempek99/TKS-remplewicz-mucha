package adapters;

import converters.MovieConverter;
import modelDTO.MovieDTO;
import movie.MovieUsecaseSuit;
import services.MovieService;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Dependent
public class MovieRepoAdapter implements MovieUsecaseSuit, Serializable {

    private MovieService movieService;
    private MovieConverter movieConverter;

    @Inject
    public MovieRepoAdapter(MovieService movieService) {
        this.movieService = movieService;
        movieConverter = new MovieConverter();
    }
    
    @Override
    public List<MovieDTO> getAllMovies() {
        List<MovieDTO> movieDTOS = new ArrayList<>();
        movieService.getAll().forEach(r -> movieDTOS.add(getMovieViaUUID(r.getId())));
        return movieDTOS;
    }
    
    @Override
    public void addMovie(MovieDTO m) {
        movieService.add(movieConverter.convertDTOToMovie(m));
    }

    @Override
    public void setMovieRented(MovieDTO m, boolean value) {
        movieService.setMovieRented(movieConverter.convertDTOToMovie(m), value);
    }

    @Override
    public void removeMovie(MovieDTO m) {
        movieService.remove(movieConverter.convertDTOToMovie(m));
    }

    @Override
    public MovieDTO getMovieViaUUID(String str) {
        return movieConverter.convertMovieToDTO(movieService.getViaUUID(str));
    }

    @Override
    public MovieDTO getMovie(MovieDTO m) {
        return movieConverter.convertMovieToDTO(movieService.get(movieConverter.convertDTOToMovie(m)));
    }

    @Override
    public void updateSingleMovie(MovieDTO movieToChange, MovieDTO movieWithData) {
        movieService.update(movieConverter.convertDTOToMovie(movieToChange), movieConverter.convertDTOToMovie(movieWithData));
    }
}
