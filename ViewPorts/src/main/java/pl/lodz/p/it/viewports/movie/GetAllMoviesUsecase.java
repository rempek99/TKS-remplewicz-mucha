package pl.lodz.p.it.viewports.movie;

import pl.lodz.p.it.viewmodel.modelDTO.MovieDTO;
import java.util.List;

public interface GetAllMoviesUsecase {
    List<MovieDTO> getAllMovies();
}
