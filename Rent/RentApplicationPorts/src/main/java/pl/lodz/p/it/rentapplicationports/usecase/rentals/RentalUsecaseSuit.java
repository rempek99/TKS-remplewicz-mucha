package pl.lodz.p.it.rentapplicationports.usecase.rentals;

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
        UpdateSingleMovieRentalUsecase
{
}
