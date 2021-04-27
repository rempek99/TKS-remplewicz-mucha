package main.java.pl.lodz.p.it.applicationports.usecase.rentals;

import pl.lodz.p.it.applicationports.usecase.rentals.*;

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
