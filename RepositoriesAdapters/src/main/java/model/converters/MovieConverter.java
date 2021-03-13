package model.converters;

import model.entities.*;
import model.*;

import java.util.Date;

public class MovieConverter {
    public static MovieEnt convertMovieToEnt(Movie movie) {
        String title = movie.getTitle();
        String author = movie.getAuthor();
        double rating= movie.getRating();
        boolean rented = movie.getRented();
        String id = movie.getId();
        String rentalUserUUID = movie.getRentalUserUUID();
        Date rentalStart = movie.getRentalStart();
        Date rentalEnd = movie.getRentalEnd();

        MovieEnt newMovie = new MovieEnt(title, author, rating, rented);
        newMovie.setId(id);
        newMovie.setRentalUserUUID(rentalUserUUID);
        newMovie.setRentalStart(rentalStart);
        newMovie.setRentalEnd(rentalEnd);

        return newMovie;
    }

    public static Movie convertEntToMovie(MovieEnt movie) {
        String title = movie.getTitle();
        String author = movie.getAuthor();
        double rating= movie.getRating();
        boolean rented = movie.getRented();
        String id = movie.getId();
        String rentalUserUUID = movie.getRentalUserUUID();
        Date rentalStart = movie.getRentalStart();
        Date rentalEnd = movie.getRentalEnd();

        Movie newMovie = new Movie(title, author, rating, rented);
        newMovie.setId(id);
        newMovie.setRentalUserUUID(rentalUserUUID);
        newMovie.setRentalStart(rentalStart);
        newMovie.setRentalEnd(rentalEnd);

        return newMovie;
    }
}
