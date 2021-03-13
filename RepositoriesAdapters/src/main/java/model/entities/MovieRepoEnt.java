package model.entities;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.*;

@ApplicationScoped
public class MovieRepoEnt {

    private List<MovieEnt> movies = Collections.synchronizedList(new ArrayList<MovieEnt>());

    @PostConstruct
    private void insertInitData() {
        movies.add(new MovieEnt("The Godfather", "Francis Ford Coppola", 9.2, false));
        movies.add(new MovieEnt("Goodfellas", "Martin Scorsese", 8.4, false));
        movies.add(new MovieEnt("Joker", "Todd Phillips", 8.7, false));
        movies.add(new MovieEnt("Fight Club", "David Fincher", 8.3, false));
        movies.add(new MovieEnt("Pulp Fiction", "Quentin Tarantino", 8.5, false));
        movies.add(new MovieEnt("Braveheart", "Mel Gibson", 8.1, false));
        movies.add(new MovieEnt("V for Vendetta", "James McTeigue", 7.3, false));
        movies.add(new MovieEnt("Requiem for a Dream", "Darren Aronofsky", 7.8, false));
        movies.add(new MovieEnt("Interstellar", "Christopher Nolan", 7.9, false));
        movies.add(new MovieEnt("Inglourious Basterds", "Quentin Tarantino", 8.0, false));
    }

    public List<MovieEnt> getAllMovies() {
        return movies;
    }

    public void addMovie(MovieEnt m) {
        movies.add(m);
        m.setId(UUID.randomUUID().toString());
        printState();
    }

    public void removeMovie(MovieEnt m) {
        movies.remove(m);
        printState();
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

    public void updateSingleMovie(MovieEnt movieToChange, MovieEnt movieWithData) {
        MovieEnt fromRepo = getMovie(movieToChange);
        fromRepo.setTitle(movieWithData.getTitle());
        fromRepo.setAuthor(movieWithData.getAuthor());
        fromRepo.setRating(movieWithData.getRating());
        fromRepo.setRented(movieWithData.getRented());
        fromRepo.setRentalUserUUID(movieWithData.getRentalUserUUID());
        fromRepo.setRentalStart(movieWithData.getRentalStart());
        fromRepo.setRentalEnd(movieWithData.getRentalEnd());
    }

    private void printState() {
        System.out.println(Arrays.toString(movies.toArray()));
    }
}
