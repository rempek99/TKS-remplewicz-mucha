package infrastructure;

import model.Movie;
import java.util.List;

public interface MoviePort {
    Movie getMovie(Movie m);
    List<Movie> getAllMovies();
    void addMovie(Movie m);
    void setMovieRented(Movie m, boolean value);
    void removeMovie(Movie m);
    Movie getMovieViaUUID(String str);
    void updateSingleMovie(Movie income, Movie outcome);
}
