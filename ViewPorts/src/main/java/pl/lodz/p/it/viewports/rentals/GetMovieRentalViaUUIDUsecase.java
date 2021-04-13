package pl.lodz.p.it.viewports.rentals;

import pl.lodz.p.it.viewmodel.modelDTO.MovieRentalDTO;

public interface GetMovieRentalViaUUIDUsecase<MR> {
    MR getMovieRentalViaUUID(String str);
}
