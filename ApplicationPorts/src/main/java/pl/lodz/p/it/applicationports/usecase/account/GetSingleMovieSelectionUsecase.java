package pl.lodz.p.it.applicationports.usecase.account;

import pl.lodz.p.it.applicationcore.domainmodel.model.Account;
import pl.lodz.p.it.applicationcore.domainmodel.model.MovieRental;

public interface GetSingleMovieSelectionUsecase {
    Account getSingleMovieSelection(MovieRental m);
}
