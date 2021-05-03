package pl.lodz.p.it.rentviewports.rentals;

import pl.lodz.p.it.rentviewmodel.modelDTO.MovieRentalDTO;

public interface GetMovieRentalViaUUIDUsecase<MR> {
    MR getMovieRentalViaUUID(String str) throws Exception;
}
