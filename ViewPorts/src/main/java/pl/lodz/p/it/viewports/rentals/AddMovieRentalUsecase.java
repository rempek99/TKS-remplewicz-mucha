package pl.lodz.p.it.viewports.rentals;

import pl.lodz.p.it.viewmodel.modelDTO.MovieRentalDTO;

public interface AddMovieRentalUsecase<MR> {
    void addMovieRental(MR r);
}
