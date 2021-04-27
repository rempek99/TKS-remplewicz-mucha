package pl.lodz.p.it.viewadapters.adapters;

import pl.lodz.p.it.rentapplicationcore.rentapplicationservice.services.MovieService;
import pl.lodz.p.it.applicationports.usecase.movie.MovieUsecaseSuit;
import pl.lodz.p.it.viewadapters.RestException;
import pl.lodz.p.it.viewadapters.converters.MovieConverter;
import pl.lodz.p.it.viewmodel.modelDTO.MovieDTO;
import pl.lodz.p.it.viewports.movie.MovieViewPortUsecaseSuit;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Dependent
public class MovieServiceAdapter implements MovieViewPortUsecaseSuit<MovieDTO>, Serializable {

    private MovieUsecaseSuit movieService;

    @Inject
    public MovieServiceAdapter(MovieService movieService) {
        this.movieService = movieService;
    }
    
    @Override
    public List<MovieDTO> getAllMovies() {
        return movieService
                .getAll()
                .stream()
                .map(MovieConverter::convertMovieToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public MovieDTO addMovie(MovieDTO m) {

        return MovieConverter.convertMovieToDTO(
                movieService.add(MovieConverter.convertDTOToMovie(m))
        );
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
        if(movieService.getViaUUID(str).isPresent()) {
            return MovieConverter.convertMovieToDTO(movieService.getViaUUID(str).get());
        }
        else
            throw new IllegalArgumentException(RestException.NOT_FOUND);
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
