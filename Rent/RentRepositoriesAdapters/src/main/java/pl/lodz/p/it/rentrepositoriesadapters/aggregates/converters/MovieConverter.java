package main.java.pl.lodz.p.it.repositoriesadapters.aggregates.converters;

import pl.lodz.p.it.applicationcore.domainmodel.model.Movie;
import pl.lodz.p.it.rentrepositoriesadapters.model_ent.entities.MovieEnt;

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
