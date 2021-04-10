package pl.lodz.p.it.viewports.account;

import pl.lodz.p.it.viewmodel.modelDTO.AccountDTO;
import pl.lodz.p.it.viewmodel.modelDTO.MovieRentalDTO;

public interface GetSingleMovieSelectionUsecase {
    AccountDTO getSingleMovieSelection(MovieRentalDTO m);
}
