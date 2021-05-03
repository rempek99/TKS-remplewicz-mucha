package pl.lodz.p.it.rentviewports.movie;

import java.util.List;

public interface GetAllMoviesUsecase<M> {
    List<M> getAllMovies();
}
