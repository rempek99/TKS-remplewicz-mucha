package usecase.rentals;

import model.MovieRental;

public interface GetMovieRentalViaUUIDUsecase {
    MovieRental getMovieRentalViaUUID(String str);
}
