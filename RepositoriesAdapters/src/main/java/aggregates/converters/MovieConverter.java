package aggregates.converters;

import model.*;
import model_ent.entities.MovieEnt;

import java.util.Date;

public class MovieConverter {

    private MovieConverter() {

    }

    public static MovieEnt convertMovieToEnt(Movie movie) {
        String title = movie.getTitle();
        String author = movie.getAuthor();
        double rating= movie.getRating();
        boolean rented = movie.isRented();
        String id = movie.getId();

        MovieEnt newMovie = new MovieEnt(title, author, rating, rented);
        newMovie.setId(id);

        return newMovie;
    }

    public static Movie convertEntToMovie(MovieEnt movie) {
        String title = movie.getTitle();
        String author = movie.getAuthor();
        double rating= movie.getRating();
        boolean rented = movie.isRented();
        String id = movie.getId();

        Movie newMovie = new Movie(title, author, rating, rented);
        newMovie.setId(id);

        return newMovie;
    }
}
