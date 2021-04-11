package pl.lodz.p.it.viewadapters.adapters;

import pl.lodz.p.it.viewadapters.converters.MovieConverter;
import pl.lodz.p.it.viewmodel.modelDTO.MovieDTO;
import pl.lodz.p.it.viewports.movie.MovieViewPortUsecaseSuit;
import pl.lodz.p.it.applicationcore.applicationservice.services.MovieService;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Dependent
public class MovieServiceAdapter implements MovieViewPortUsecaseSuit, Serializable {

    private MovieService movieService;

    @Inject
    public MovieServiceAdapter(MovieService movieService) {
        this.movieService = movieService;
    }
    
    @Override
    public List<MovieDTO> getAllMovies() {
        List<MovieDTO> movieDTOS = new ArrayList<>();
        movieService
                .getAll()
                .forEach(
                        r -> movieDTOS.add(getMovieViaUUID(r.getId()))
                );
        return movieDTOS;
    }
    
    @Override
    public void addMovie(MovieDTO m) {
        movieService.add(MovieConverter.convertDTOToMovie(m));
    }

    @Override
    public void setMovieRented(MovieDTO m, boolean value) {
        movieService.setMovieRented(MovieConverter.convertDTOToMovie(m), value);
    }

    @Override
    public void removeMovie(MovieDTO m) {
        movieService.remove(MovieConverter.convertDTOToMovie(m));
    }

    @Override
    public MovieDTO getMovieViaUUID(String str) {
        return MovieConverter.convertMovieToDTO(movieService.getViaUUID(str));
    }

    @Override
    public MovieDTO getMovie(MovieDTO m) {
        return MovieConverter.convertMovieToDTO(movieService.get(MovieConverter.convertDTOToMovie(m)));
    }

    @Override
    public void updateSingleMovie(MovieDTO movieToChange, MovieDTO movieWithData) {
        movieService.update(
                MovieConverter.convertDTOToMovie(movieToChange),
                MovieConverter.convertDTOToMovie(movieWithData)
        );
    }
}
