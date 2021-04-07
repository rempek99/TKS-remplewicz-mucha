package movie;

import modelDTO.MovieDTO;

public interface SetMovieRentedUsecase {
    void setMovieRented(MovieDTO m, boolean value);
}
