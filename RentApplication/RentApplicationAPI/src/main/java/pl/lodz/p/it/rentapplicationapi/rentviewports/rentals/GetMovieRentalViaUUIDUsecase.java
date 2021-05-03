package pl.lodz.p.it.rentapplicationapi.rentviewports.rentals;


public interface GetMovieRentalViaUUIDUsecase<MR> {
    MR getMovieRentalViaUUID(String str) throws Exception;
}
