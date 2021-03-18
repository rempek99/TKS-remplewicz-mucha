package usecase.rentals;

import model.MovieRental;
import java.util.List;

public interface GetAllMovieRentalsUsecase {
    List<MovieRental> getAllMovieRentals();
}
