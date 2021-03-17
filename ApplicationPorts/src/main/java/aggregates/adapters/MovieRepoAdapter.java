package aggregates.adapters;

import infrastructure.MoviePort;
import model.Movie;
import model_ent.entities.MovieEnt;
import model_ent.repositories.MovieEntRepo;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static aggregates.converters.MovieConverter.convertEntToMovie;
import static aggregates.converters.MovieConverter.convertMovieToEnt;

@Dependent
public class MovieRepoAdapter implements MoviePort, Serializable {

    private final MovieEntRepo movieRepo;
    private  List<MovieEnt> movies;

    @Inject
    public MovieRepoAdapter(MovieEntRepo movieRepo) {
        this.movieRepo = movieRepo;
        movies = movieRepo.getAllMovies();
    }

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
    public void setMovieRented(Movie m, boolean value) {
        movieRepo.setMovieRented(convertMovieToEnt(m), value);
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
        fromRepo.setRented(movieWithData.isRented());
    }
}
