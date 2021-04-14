package pl.lodz.p.it.repositoriesadapters.model_ent.repositories;

import pl.lodz.p.it.repositoriesadapters.model_ent.entities.MovieEnt;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.*;

@ApplicationScoped
public class MovieEntRepo implements IRepositoryEnt<MovieEnt> {

    private List<MovieEnt> movies = Collections.synchronizedList(new ArrayList<MovieEnt>());

    @PostConstruct
    private void insertInitData() {
        try {
            add(new MovieEnt("The Godfather", "Francis Ford Coppola", 9.2, false));
            add(new MovieEnt("Goodfellas", "Martin Scorsese", 8.4, false));
            add(new MovieEnt("Joker", "Todd Phillips", 8.7, false));
            add(new MovieEnt("Fight Club", "David Fincher", 8.3, false));
            add(new MovieEnt("Pulp Fiction", "Quentin Tarantino", 8.5, false));
            add(new MovieEnt("Braveheart", "Mel Gibson", 8.1, false));
            add(new MovieEnt("V for Vendetta", "James McTeigue", 7.3, false));
            add(new MovieEnt("Requiem for a Dream", "Darren Aronofsky", 7.8, false));
            add(new MovieEnt("Interstellar", "Christopher Nolan", 7.9, false));
            add(new MovieEnt("Inglourious Basterds", "Quentin Tarantino", 8.0, false));
        } catch (RepositoryException e) {
            e.printStackTrace();
        }

    }

    public List<MovieEnt> getAll() {
        return Collections.unmodifiableList(movies);
    }

    public MovieEnt add(MovieEnt m) throws RepositoryException {
        if (movies
                .stream()
                .anyMatch(
                        movie -> movie.getAuthor().equals(m.getAuthor())
                                &&
                                movie.getTitle().equals(m.getTitle())
                ))
            throw new RepositoryException(RepositoryException.DUPLICATED);

        m.setId(UUID.randomUUID().toString());
        movies.add(m);
        return m;

    }

    public void setMovieRented(MovieEnt m, boolean value) {
        for (MovieEnt movie : movies) {
            if (movie.getId().equals(m.getId())) {
                movie.setRented(value);
            }
        }
    }

    public void remove(MovieEnt m) {
        movies.remove(m);
    }

    public MovieEnt getViaUUID(String str) {
        for (MovieEnt movie : movies) {
            if (movie.getId().equals(str)) {
                return movie;
            }
        }
        return null;
    }

    public MovieEnt get(MovieEnt m) {
        return movies.stream()
                .filter(x -> x.equals(m))
                .findFirst()
                .orElse(null);
    }

    public MovieEnt update(MovieEnt movieToChange, MovieEnt movieWithData) {
        MovieEnt fromRepo = get(movieToChange);
        fromRepo.setTitle(movieWithData.getTitle());
        fromRepo.setAuthor(movieWithData.getAuthor());
        fromRepo.setRating(movieWithData.getRating());
        fromRepo.setRented(movieWithData.isRented());
        return fromRepo;
    }
}
