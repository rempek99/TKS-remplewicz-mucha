package pl.lodz.p.it.applicationports.usecase.rentals;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.MovieRental;
import java.util.List;

public interface GetAllMovieRentalsUsecase {
    List<MovieRental> getAllMovieRentals();
}
