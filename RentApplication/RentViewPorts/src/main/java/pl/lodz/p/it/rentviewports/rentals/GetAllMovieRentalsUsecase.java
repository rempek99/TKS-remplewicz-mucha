package pl.lodz.p.it.rentviewports.rentals;

import pl.lodz.p.it.rentviewmodel.modelDTO.MovieRentalDTO;
import java.util.List;

public interface GetAllMovieRentalsUsecase<MR> {
    List<MR> getAllMovieRentals();
}
