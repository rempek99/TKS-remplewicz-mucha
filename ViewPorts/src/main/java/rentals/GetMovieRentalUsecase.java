package rentals;

import modelDTO.MovieRentalDTO;

public interface GetMovieRentalUsecase {
    MovieRentalDTO getMovieRental(MovieRentalDTO m);
}
