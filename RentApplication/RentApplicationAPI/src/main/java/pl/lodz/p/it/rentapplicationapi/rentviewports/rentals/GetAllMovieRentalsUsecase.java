package pl.lodz.p.it.rentapplicationapi.rentviewports.rentals;

import java.util.List;

public interface GetAllMovieRentalsUsecase<MR> {
    List<MR> getAllMovieRentals();
}
