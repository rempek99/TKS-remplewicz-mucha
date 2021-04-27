package pl.lodz.p.it.rentviewports.rentals;

import pl.lodz.p.it.rentviewmodel.modelDTO.MovieRentalDTO;

public interface RemoveMovieRentalUsecase<MR> {
    void removeMovieRental(MR r);
}
