package usecase;

import model.MovieRental;

public interface GetMovieRentalViaUUIDUsecase {
    MovieRental getMovieRentalViaUUID(String str);
}
