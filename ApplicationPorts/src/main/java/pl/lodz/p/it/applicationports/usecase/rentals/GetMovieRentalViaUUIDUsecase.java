package pl.lodz.p.it.applicationports.usecase.rentals;

import pl.lodz.p.it.applicationcore.domainmodel.model.MovieRental;

import java.util.Optional;

public interface GetMovieRentalViaUUIDUsecase {
    Optional<MovieRental> getMovieRentalViaUUID(String str);
}
