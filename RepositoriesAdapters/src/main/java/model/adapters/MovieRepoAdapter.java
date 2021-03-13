package model.adapters;

import model.entities.*;
import infrastructure.*;
import model.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static model.converters.MovieConverter.convertMovieToEnt;
import static model.converters.MovieConverter.convertEntToMovie;

public class MovieRepoAdapter implements MoviePort {
    @Inject
    private MovieRepoEnt movieRepo;

    private List<MovieEnt> movies = movieRepo.getAllMovies();

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> temp = Collections.synchronizedList(new ArrayList<Movie>());
        for (MovieEnt movie: movies) {
            temp.add(convertEntToMovie(movie));
        }
        return temp;
    }


    @Override
    public void addMovie(Movie m) {
        movieRepo.addMovie(convertMovieToEnt(m));
    }


    @Override
    public void removeMovie(Movie m) {
        movieRepo.removeMovie(convertMovieToEnt(m));
    }


    @Override
    public Movie getMovieViaUUID(String str) {
        for(MovieEnt movie: movies) {
            if(movie.getId().equals(str)){
                return convertEntToMovie(movie);
            }
        }
        return null;
    }

    @Override
    public Movie getMovie(Movie m) {
        if (movies.contains(m)) {
            return m;
        } else {
            return null;
        }
    }


    @Override
    public void updateSingleMovie(Movie movieToChange, Movie movieWithData) {
        Movie fromRepo = getMovie(movieToChange);
        fromRepo.setTitle(movieWithData.getTitle());
        fromRepo.setAuthor(movieWithData.getAuthor());
        fromRepo.setRating(movieWithData.getRating());
        fromRepo.setRented(movieWithData.getRented());
        fromRepo.setRentalUserUUID(movieWithData.getRentalUserUUID());
        fromRepo.setRentalStart(movieWithData.getRentalStart());
        fromRepo.setRentalEnd(movieWithData.getRentalEnd());
    }
}
