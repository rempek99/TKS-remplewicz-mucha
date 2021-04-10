package pl.lodz.p.it.viewports.rentals;

import pl.lodz.p.it.viewmodel.modelDTO.MovieRentalDTO;

public interface UpdateSingleMovieRentalUsecase {
    void updateSingleMovieRental(MovieRentalDTO income, MovieRentalDTO outcome);
}
