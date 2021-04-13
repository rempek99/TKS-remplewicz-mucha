package pl.lodz.p.it.viewports.account;

import pl.lodz.p.it.viewmodel.modelDTO.AccountDTO;
import pl.lodz.p.it.viewmodel.modelDTO.MovieRentalDTO;

public interface GetSingleMovieSelectionUsecase<A,MR> {
    A getSingleMovieSelection(MR m);
}
