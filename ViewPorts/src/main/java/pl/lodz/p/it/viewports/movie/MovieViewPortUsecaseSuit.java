package pl.lodz.p.it.viewports.movie;

public interface MovieViewPortUsecaseSuit<M>
extends
        AddMovieUsecase<M>,
        GetAllMoviesUsecase<M>,
        GetMovieViaUUIDUsecase<M>,
        RemoveMovieUsecase<M>,
        UpdateSingleMovieUsecase<M>,
        SetMovieRentedUsecase<M>,
        GetMovieUsecase<M>
{
}
