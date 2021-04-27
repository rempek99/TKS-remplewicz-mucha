package pl.lodz.p.it.rentapplicationports.usecase.movie;

public interface MovieUsecaseSuit
extends
        AddMovieUsecase,
        GetAllMoviesUsecase,
        GetMovieViaUUIDUsecase,
        GetMovieUsecase,
        RemoveMovieUsecase,
        UpdateSingleMovieUsecase,
        SetMovieRentedUsecase
{
}
