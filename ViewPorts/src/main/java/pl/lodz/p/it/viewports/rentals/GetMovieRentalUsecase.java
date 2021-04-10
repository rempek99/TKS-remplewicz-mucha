package pl.lodz.p.it.viewports.rentals;

import pl.lodz.p.it.viewmodel.modelDTO.MovieRentalDTO;

public interface GetMovieRentalUsecase {
    MovieRentalDTO getMovieRental(MovieRentalDTO m);
}
