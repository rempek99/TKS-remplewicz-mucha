package dtoadapters;

import controller.MovieService;
import dtoconverters.*;
import dtomodel.MovieDTO;
import model.Movie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static dtoconverters.MovieDTOConverter.convertMovieToDTO;
import static dtoconverters.MovieDTOConverter.convertDTOToMovie;

public class MovieDTOAdapter {
    final MovieService movieService;
    final MovieDTOConverter MovieDTOConverter;

    public MovieDTOAdapter(MovieService movieService, dtoconverters.MovieDTOConverter movieDTOConverter) {
        this.movieService = movieService;
        MovieDTOConverter = movieDTOConverter;
    }

    public List<MovieDTO> getAllMovies() {
        List<MovieDTO> temp = Collections.synchronizedList(new ArrayList<MovieDTO>());
        List<Movie> temp2 = movieService.getAllMovies();
        for (Movie movie: temp2) {
            temp.add(convertMovieToDTO(movie));
        }
        return temp;
    }

    public void addMovie(MovieDTO b) {
        movieService.addMovie(convertDTOToMovie(b));
    }

    public void removeMovie(MovieDTO b) {
        movieService.removeMovie(convertDTOToMovie(b));
    }

    public MovieDTO getMovieViaUUID(String str) {
        List<MovieDTO> temp = getAllMovies();
        for(MovieDTO movie: temp) {
            if(movie.getId().equals(str)){
                return movie;
            }
        }
        return null;
    }

    public MovieDTO getMovie(MovieDTO b) {
        List<MovieDTO> temp = getAllMovies();
        if (temp.contains(b)) {
            return b;
        } else {
            return null;
        }
    }

    public void updateSingleMovie(MovieDTO movieToChange, MovieDTO movieWithData) {
        MovieDTO fromRepo = getMovie(movieToChange);
        fromRepo.setTitle(movieWithData.getTitle());
        fromRepo.setAuthor(movieWithData.getAuthor());
        fromRepo.setRating(movieWithData.getRating());
        fromRepo.setRented(movieWithData.getRented());
        fromRepo.setRentalUserUUID(movieWithData.getRentalUserUUID());
        fromRepo.setRentalStart(movieWithData.getRentalStart());
        fromRepo.setRentalEnd(movieWithData.getRentalEnd());
    }
}
