package rentals;

import modelDTO.MovieRentalDTO;

public interface UpdateSingleMovieRentalUsecase {
    void updateSingleMovieRental(MovieRentalDTO income, MovieRentalDTO outcome);
}
