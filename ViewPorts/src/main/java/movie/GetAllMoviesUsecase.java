package movie;

import modelDTO.MovieDTO;
import java.util.List;

public interface GetAllMoviesUsecase {
    List<MovieDTO> getAllMovies();
}
