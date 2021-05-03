package pl.lodz.p.it.rentapplicationports.usecase.rentals;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.MovieRental;

public interface AddMovieRentalUsecase {
    MovieRental addMovieRental(MovieRental r);
}
