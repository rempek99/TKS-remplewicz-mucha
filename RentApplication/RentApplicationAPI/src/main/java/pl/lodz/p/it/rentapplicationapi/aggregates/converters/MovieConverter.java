package pl.lodz.p.it.rentapplicationapi.aggregates.converters;

import pl.lodz.p.it.rentapplicationapi.rentviewmodel.modelDTO.MovieDTO;
import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Movie;


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

    public static MovieDTO convertFromMovieKafkaDTO(pl.lodz.p.it.topicmodels.dtos.MovieDTO value) {
        return new MovieDTO(value.getTitle(), value.getAuthor(), value.getRating(), value.isRented());
    }
}