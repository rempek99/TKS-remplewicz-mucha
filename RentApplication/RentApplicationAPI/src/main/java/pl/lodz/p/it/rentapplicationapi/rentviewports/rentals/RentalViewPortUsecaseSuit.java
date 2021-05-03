package pl.lodz.p.it.rentapplicationapi.rentviewports.rentals;

public interface RentalViewPortUsecaseSuit<BR,MR>
extends
        AddBookRentalUsecase<BR>,
        AddMovieRentalUsecase<MR>,
        GetAllBookRentalsUsecase<BR>,
        GetAllMovieRentalsUsecase<MR>,
        GetBookRentalViaUUIDUsecase<BR>,
        GetDisabledDaysUsecase,
        GetMovieRentalViaUUIDUsecase<MR>,
        RemoveMovieRentalUsecase<MR>,
        RemoveBookRentalUsecase<BR>,
        UpdateSingleBookRentalUsecase<BR>,
        UpdateSingleMovieRentalUsecase<MR>,
        GetMovieRentalUsecase<MR>,
        GetBookRentalUsecase<BR>
{
}
