package pl.lodz.p.it.applicationports.usecase.movie;

import pl.lodz.p.it.applicationcore.domainmodel.model.Movie;

public interface UpdateSingleMovieUsecase {
    void update(Movie income, Movie outcome);
}
