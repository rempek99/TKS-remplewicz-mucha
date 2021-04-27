package pl.lodz.p.it.soap.aggregates.converters;

import pl.lodz.p.it.soap.model.MovieSoap;

public class MovieSoapConverter {

    private MovieSoapConverter() {

    }

    public static MovieSoap convertMovieToMovieSoap(Movie movie) {
        MovieSoap newMovie = new MovieSoap(
                movie.getTitle(),
                movie.getAuthor(),
                movie.getRating(),
                movie.isRented());
        newMovie.setId(movie.getId());
        return newMovie;
    }

    public static Movie convertMovieSoapToMovie(MovieSoap movie) {
        Movie newMovie = new Movie(
                movie.getTitle(),
                movie.getAuthor(),
                movie.getRating(),
                movie.isRented());
        newMovie.setId(movie.getId());
        return newMovie;
    }
}
