package pl.lodz.p.it.viewports.rentals;

public interface GetMovieRentalUsecase<MR> {
    MR getMovieRental(MR m) throws Exception;
}
