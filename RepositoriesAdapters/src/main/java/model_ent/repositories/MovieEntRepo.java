package model_ent.repositories;

import model_ent.entities.MovieEnt;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.*;

@ApplicationScoped
public class MovieEntRepo {

    private List<MovieEnt> movies = Collections.synchronizedList(new ArrayList<MovieEnt>());

    @PostConstruct
    private void insertInitData() {
        addMovie(new MovieEnt("The Godfather", "Francis Ford Coppola", 9.2, false));
        addMovie(new MovieEnt("Goodfellas", "Martin Scorsese", 8.4, false));
        addMovie(new MovieEnt("Joker", "Todd Phillips", 8.7, false));
        addMovie(new MovieEnt("Fight Club", "David Fincher", 8.3, false));
        addMovie(new MovieEnt("Pulp Fiction", "Quentin Tarantino", 8.5, false));
        addMovie(new MovieEnt("Braveheart", "Mel Gibson", 8.1, false));
        addMovie(new MovieEnt("V for Vendetta", "James McTeigue", 7.3, false));
        addMovie(new MovieEnt("Requiem for a Dream", "Darren Aronofsky", 7.8, false));
        addMovie(new MovieEnt("Interstellar", "Christopher Nolan", 7.9, false));
        addMovie(new MovieEnt("Inglourious Basterds", "Quentin Tarantino", 8.0, false));
    }

    public List<MovieEnt> getAllMovies() {
        return Collections.unmodifiableList(movies);
    }

    public MovieEnt addMovie(MovieEnt m) {
            m.setId(UUID.randomUUID().toString());
            movies.add(m);
        return m;
    }

    public void setMovieRented(MovieEnt m, boolean value) {
        for(MovieEnt movie: movies) {
            if(movie.getId().equals(m.getId())){
                movie.setRented(value);
            }
        }
    }

    public void removeMovie(MovieEnt m) {
            movies.remove(m);
    }

    public MovieEnt getMovieViaUUID(String str) {
        for(MovieEnt movie: movies) {
            if(movie.getId().equals(str)){
                return movie;
            }
        }
        return null;
    }

    public MovieEnt getMovie(MovieEnt m) {
        if (movies.contains(m)) {
            return m;
        } else {
            return null;
        }
    }

    public MovieEnt updateSingleMovie(MovieEnt movieToChange, MovieEnt movieWithData) {
        MovieEnt fromRepo = getMovie(movieToChange);
        fromRepo.setTitle(movieWithData.getTitle());
        fromRepo.setAuthor(movieWithData.getAuthor());
        fromRepo.setRating(movieWithData.getRating());
        fromRepo.setRented(movieWithData.isRented());
        fromRepo.setRentalUserUUID(movieWithData.getRentalUserUUID());
        fromRepo.setRentalStart(movieWithData.getRentalStart());
        fromRepo.setRentalEnd(movieWithData.getRentalEnd());
        return fromRepo;
    }
}
