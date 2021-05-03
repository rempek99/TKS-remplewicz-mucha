package pl.lodz.p.it.rentapplicationapi.rentviewports.movie;

import java.util.List;

public interface GetAllMoviesUsecase<M> {
    List<M> getAllMovies();
}
