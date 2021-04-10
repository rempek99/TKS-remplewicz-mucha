package pl.lodz.p.it.viewports.movie;

import pl.lodz.p.it.viewmodel.modelDTO.MovieDTO;

public interface UpdateSingleMovieUsecase {
    void updateSingleMovie(MovieDTO income, MovieDTO outcome);
}
