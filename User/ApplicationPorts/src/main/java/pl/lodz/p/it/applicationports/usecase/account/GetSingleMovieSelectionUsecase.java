package pl.lodz.p.it.applicationports.usecase.account;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Account;
import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.MovieRental;

public interface GetSingleMovieSelectionUsecase {
    Account getSingleMovieSelection(MovieRental m);
}
