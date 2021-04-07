package converters;

import model.Movie;
import modelDTO.MovieDTO;

public class MovieConverter {

    public MovieConverter() {
    }

    public static MovieDTO convertMovieToDTO(Movie movie) {
        String title = movie.getTitle();
        String author = movie.getAuthor();
        double rating= movie.getRating();
        boolean rented = movie.isRented();
        String id = movie.getId();

        MovieDTO newMovie = new MovieDTO(title, author, rating, rented);
        newMovie.setId(id);

        return newMovie;
    }

    public static Movie convertDTOToMovie(MovieDTO movie) {
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