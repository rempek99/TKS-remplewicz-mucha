package pl.lodz.p.it.rentapplicationports.usecase.rentals;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.MovieRental;

public interface UpdateSingleMovieRentalUsecase {
    void updateSingleMovieRental(MovieRental income, MovieRental outcome);
}
