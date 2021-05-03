package pl.lodz.p.it.rentapplicationports.usecase.movie;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Movie;

import java.util.Optional;

public interface GetMovieViaUUIDUsecase {
    Optional<Movie> getViaUUID(String str);
}
