package pl.lodz.p.it.applicationports.usecase.rentals;

import pl.lodz.p.it.applicationcore.domainmodel.model.MovieRental;

public interface AddMovieRentalUsecase {
    MovieRental addMovieRental(MovieRental r);
}
