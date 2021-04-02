package rentals;

import modelDTO.MovieRentalDTO;
import java.util.List;

public interface GetAllMovieRentalsUsecase {
    List<MovieRentalDTO> getAllMovieRentals();
}
