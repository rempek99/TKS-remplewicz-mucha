package pl.lodz.p.it.applicationports.usecase.movie;

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
