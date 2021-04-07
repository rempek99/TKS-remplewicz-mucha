package movie;

public interface MovieUsecaseSuit
extends
        AddMovieUsecase,
        GetAllMoviesUsecase,
        GetMovieViaUUIDUsecase,
        RemoveMovieUsecase,
        UpdateSingleMovieUsecase,
        SetMovieRentedUsecase,
        GetMovieUsecase
{
}
