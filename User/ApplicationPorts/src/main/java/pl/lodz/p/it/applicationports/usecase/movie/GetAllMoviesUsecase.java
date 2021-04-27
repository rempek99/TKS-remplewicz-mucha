package pl.lodz.p.it.applicationports.usecase.movie;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Movie;
import java.util.List;

public interface GetAllMoviesUsecase {
    List<Movie> getAll();
}
