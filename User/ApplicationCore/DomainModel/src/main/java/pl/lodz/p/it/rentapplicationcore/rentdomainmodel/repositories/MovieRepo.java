package pl.lodz.p.it.rentapplicationcore.rentdomainmodel.repositories;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Movie;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.*;

@ApplicationScoped
public class MovieRepo implements IRepository<Movie> {

    private List<Movie> movies = Collections.synchronizedList(new ArrayList<Movie>());

    @PostConstruct
    private void insertInitData() {
        movies.add(new Movie("The Godfather", "Francis Ford Coppola", 9.2, false));
        movies.add(new Movie("Goodfellas", "Martin Scorsese", 8.4, false));
        movies.add(new Movie("Joker", "Todd Phillips", 8.7, false));
        movies.add(new Movie("Fight Club", "David Fincher", 8.3, false));
        movies.add(new Movie("Pulp Fiction", "Quentin Tarantino", 8.5, false));
        movies.add(new Movie("Braveheart", "Mel Gibson", 8.1, false));
        movies.add(new Movie("V for Vendetta", "James McTeigue", 7.3, false));
        movies.add(new Movie("Requiem for a Dream", "Darren Aronofsky", 7.8, false));
        movies.add(new Movie("Interstellar", "Christopher Nolan", 7.9, false));
        movies.add(new Movie("Inglourious Basterds", "Quentin Tarantino", 8.0, false));
    }

    public List<Movie> getAll() {
        return movies;
    }

    public Movie add(Movie m) {
            movies.add(m);
            m.setId(UUID.randomUUID().toString());
        return m;
    }

    public void remove(Movie m) {
            movies.remove(m);
    }

    public Movie getViaUUID(String str) {
        for(Movie movie: movies) {
            if(movie.getId().equals(str)){
                return movie;
            }
        }
        return null;
    }

    public Movie get(Movie m) {
        if (movies.contains(m)) {
            return m;
        } else {
            return null;
        }
    }

    public Movie update(Movie movieToChange, Movie movieWithData) {
        Movie fromRepo = get(movieToChange);
        fromRepo.setTitle(movieWithData.getTitle());
        fromRepo.setAuthor(movieWithData.getAuthor());
        fromRepo.setRating(movieWithData.getRating());
        fromRepo.setRented(movieWithData.isRented());
        return fromRepo;
    }
}
