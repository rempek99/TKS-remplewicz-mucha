package pl.lodz.p.it.rentapplicationports.infrastructure;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Movie;
import java.util.List;
import java.util.Optional;

public interface MoviePort {
    Movie getMovie(Movie m);
    List<Movie> getAllMovies();
    Movie addMovie(Movie m);
    void setMovieRented(Movie m, boolean value);
    void removeMovie(Movie m);
    Optional<Movie> getMovieViaUUID(String str);
    void updateSingleMovie(Movie income, Movie outcome);
}
