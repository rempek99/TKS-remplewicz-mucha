package pl.lodz.p.it.viewports.movie;

import pl.lodz.p.it.viewmodel.modelDTO.MovieDTO;

public interface SetMovieRentedUsecase {
    void setMovieRented(MovieDTO m, boolean value);
}
