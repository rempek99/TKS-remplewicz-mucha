package pl.lodz.p.it.viewadapters.converters;

import pl.lodz.p.it.applicationcore.domainmodel.model.Movie;
import pl.lodz.p.it.viewmodel.modelDTO.MovieDTO;

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