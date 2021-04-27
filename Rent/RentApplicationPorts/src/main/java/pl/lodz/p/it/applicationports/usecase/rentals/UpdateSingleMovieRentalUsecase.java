package main.java.pl.lodz.p.it.applicationports.usecase.rentals;

import pl.lodz.p.it.applicationcore.domainmodel.model.MovieRental;

public interface UpdateSingleMovieRentalUsecase {
    void updateSingleMovieRental(MovieRental income, MovieRental outcome);
}
