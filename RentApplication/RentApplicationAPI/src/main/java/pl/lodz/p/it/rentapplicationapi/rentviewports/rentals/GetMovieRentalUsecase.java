package pl.lodz.p.it.rentapplicationapi.rentviewports.rentals;

public interface GetMovieRentalUsecase<MR> {
    MR getMovieRental(MR m) throws Exception;
}
