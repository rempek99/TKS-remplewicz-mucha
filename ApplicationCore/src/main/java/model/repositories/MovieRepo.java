package model.repositories;

import model.Movie;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.*;

@ApplicationScoped
public class MovieRepo {

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

    public List<Movie> getAllMovies() {
        return movies;
    }

    public Movie addMovie(Movie m) {
            movies.add(m);
            m.setId(UUID.randomUUID().toString());
//            printState();
        return m;
    }

    public void removeMovie(Movie m) {
            movies.remove(m);
//            printState();
    }

    public Movie getMovieViaUUID(String str) {
        for(Movie movie: movies) {
            if(movie.getId().equals(str)){
                return movie;
            }
        }
        return null;
    }

    public Movie getMovie(Movie m) {
        if (movies.contains(m)) {
            return m;
        } else {
            return null;
        }
    }

    public Movie updateSingleMovie(Movie movieToChange, Movie movieWithData) {
        Movie fromRepo = getMovie(movieToChange);
        fromRepo.setTitle(movieWithData.getTitle());
        fromRepo.setAuthor(movieWithData.getAuthor());
        fromRepo.setRating(movieWithData.getRating());
        fromRepo.setRented(movieWithData.getRented());
        fromRepo.setRentalUserUUID(movieWithData.getRentalUserUUID());
        fromRepo.setRentalStart(movieWithData.getRentalStart());
        fromRepo.setRentalEnd(movieWithData.getRentalEnd());
        return fromRepo;
    }

//    private void printState() {
//        System.out.println(Arrays.toString(movies.toArray()));
//    }
}
