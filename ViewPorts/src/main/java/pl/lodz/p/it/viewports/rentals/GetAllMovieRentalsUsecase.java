package pl.lodz.p.it.viewports.rentals;

import pl.lodz.p.it.viewmodel.modelDTO.MovieRentalDTO;
import java.util.List;

public interface GetAllMovieRentalsUsecase {
    List<MovieRentalDTO> getAllMovieRentals();
}
