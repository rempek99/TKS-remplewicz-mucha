package pl.lodz.p.it.rentviewadapters.converters;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Movie;
import pl.lodz.p.it.rentviewmodel.modelDTO.MovieDTO;

public class MovieConverter {

    private MovieConverter() {
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