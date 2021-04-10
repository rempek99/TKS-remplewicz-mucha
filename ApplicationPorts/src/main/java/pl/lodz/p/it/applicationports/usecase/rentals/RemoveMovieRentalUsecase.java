package pl.lodz.p.it.applicationports.usecase.rentals;

import pl.lodz.p.it.applicationcore.domainmodel.model.MovieRental;

public interface RemoveMovieRentalUsecase {
    void removeMovieRental(MovieRental r);
}
