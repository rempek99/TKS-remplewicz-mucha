package pl.lodz.p.it.rentapplicationapi.rentviewports.movie;

public interface UpdateSingleMovieUsecase<M> {
    void updateSingleMovie(M income, M outcome);
}
