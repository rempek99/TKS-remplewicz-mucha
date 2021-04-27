package pl.lodz.p.it.rentapplicationports.usecase.movie;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Movie;

public interface SetMovieRentedUsecase {
    void setMovieRented(Movie m, boolean value);
}
