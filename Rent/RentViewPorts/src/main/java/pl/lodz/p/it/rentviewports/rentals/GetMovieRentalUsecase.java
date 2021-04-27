package pl.lodz.p.it.rentviewports.rentals;

public interface GetMovieRentalUsecase<MR> {
    MR getMovieRental(MR m) throws Exception;
}
