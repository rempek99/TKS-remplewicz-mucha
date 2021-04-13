package pl.lodz.p.it.viewports.rentals;

import pl.lodz.p.it.viewmodel.modelDTO.MovieRentalDTO;

public interface RemoveMovieRentalUsecase<MR> {
    void removeMovieRental(MR r);
}
