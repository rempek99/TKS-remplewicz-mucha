package rentals;

import modelDTO.MovieRentalDTO;

public interface GetMovieRentalViaUUIDUsecase {
    MovieRentalDTO getMovieRentalViaUUID(String str);
}
