package dtoconverters;

import dtomodel.MovieDTO;
import model.Movie;

import java.util.Date;

public class MovieDTOConverter {
    public static MovieDTO convertMovieToDTO(Movie movie) {
        String title = movie.getTitle();
        String author = movie.getAuthor();
        double rating= movie.getRating();
        boolean rented = movie.getRented();
        String id = movie.getId();
        String rentalUserUUID = movie.getRentalUserUUID();
        Date rentalStart = movie.getRentalStart();
        Date rentalEnd = movie.getRentalEnd();

        MovieDTO newMovie = new MovieDTO(title, author, rating, rented);
        newMovie.setId(id);
        newMovie.setRentalUserUUID(rentalUserUUID);
        newMovie.setRentalStart(rentalStart);
        newMovie.setRentalEnd(rentalEnd);

        return newMovie;
    }

    public static Movie convertDTOToMovie(MovieDTO movie) {
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
