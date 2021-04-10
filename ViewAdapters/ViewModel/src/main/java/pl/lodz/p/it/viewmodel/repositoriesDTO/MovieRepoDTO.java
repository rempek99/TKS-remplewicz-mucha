package pl.lodz.p.it.viewmodel.repositoriesDTO;

import pl.lodz.p.it.viewmodel.modelDTO.MovieDTO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.*;

@ApplicationScoped
public class MovieRepoDTO implements IRepository<MovieDTO> {

    private List<MovieDTO> movies = Collections.synchronizedList(new ArrayList<MovieDTO>());

    @PostConstruct
    private void insertInitData() {
        movies.add(new MovieDTO("The Godfather", "Francis Ford Coppola", 9.2, false));
        movies.add(new MovieDTO("Goodfellas", "Martin Scorsese", 8.4, false));
        movies.add(new MovieDTO("Joker", "Todd Phillips", 8.7, false));
        movies.add(new MovieDTO("Fight Club", "David Fincher", 8.3, false));
        movies.add(new MovieDTO("Pulp Fiction", "Quentin Tarantino", 8.5, false));
        movies.add(new MovieDTO("Braveheart", "Mel Gibson", 8.1, false));
        movies.add(new MovieDTO("V for Vendetta", "James McTeigue", 7.3, false));
        movies.add(new MovieDTO("Requiem for a Dream", "Darren Aronofsky", 7.8, false));
        movies.add(new MovieDTO("Interstellar", "Christopher Nolan", 7.9, false));
        movies.add(new MovieDTO("Inglourious Basterds", "Quentin Tarantino", 8.0, false));
    }

    public List<MovieDTO> getAll() {
        return movies;
    }

    public MovieDTO add(MovieDTO m) {
            movies.add(m);
            m.setId(UUID.randomUUID().toString());
        return m;
    }

    public void remove(MovieDTO m) {
            movies.remove(m);
    }

    public MovieDTO getViaUUID(String str) {
        for(MovieDTO movie: movies) {
            if(movie.getId().equals(str)){
                return movie;
            }
        }
        return null;
    }

    public MovieDTO get(MovieDTO m) {
        if (movies.contains(m)) {
            return m;
        } else {
            return null;
        }
    }

    public MovieDTO update(MovieDTO movieToChange, MovieDTO movieWithData) {
        MovieDTO fromRepo = get(movieToChange);
        fromRepo.setTitle(movieWithData.getTitle());
        fromRepo.setAuthor(movieWithData.getAuthor());
        fromRepo.setRating(movieWithData.getRating());
        fromRepo.setRented(movieWithData.isRented());
        return fromRepo;
    }
}
