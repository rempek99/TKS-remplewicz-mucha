package pl.lodz.p.it.viewports.movie;

import java.util.List;

public interface GetAllMoviesUsecase<M> {
    List<M> getAllMovies();
}
