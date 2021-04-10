package pl.lodz.p.it.applicationports.usecase.movie;

import pl.lodz.p.it.applicationcore.domainmodel.model.Movie;

public interface GetMovieViaUUIDUsecase {
    Movie getViaUUID(String str);
}
