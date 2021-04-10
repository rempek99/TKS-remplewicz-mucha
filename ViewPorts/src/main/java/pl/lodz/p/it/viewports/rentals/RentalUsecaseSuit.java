package pl.lodz.p.it.viewports.rentals;

public interface RentalUsecaseSuit
extends
        AddBookRentalUsecase,
        AddMovieRentalUsecase,
        GetAllBookRentalsUsecase,
        GetAllMovieRentalsUsecase,
        GetBookRentalViaUUIDUsecase,
        GetDisabledDaysUsecase,
        GetMovieRentalViaUUIDUsecase,
        RemoveMovieRentalUsecase,
        RemoveBookRentalUsecase,
        UpdateSingleBookRentalUsecase,
        UpdateSingleMovieRentalUsecase,
        GetMovieRentalUsecase,
        GetBookRentalUsecase
{
}
