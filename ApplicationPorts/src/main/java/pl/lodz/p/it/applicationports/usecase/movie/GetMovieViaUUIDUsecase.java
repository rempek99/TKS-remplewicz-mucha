package pl.lodz.p.it.applicationports.usecase.movie;

import pl.lodz.p.it.applicationcore.domainmodel.model.Movie;

import java.util.Optional;

public interface GetMovieViaUUIDUsecase {
    Optional<Movie> getViaUUID(String str);
}
