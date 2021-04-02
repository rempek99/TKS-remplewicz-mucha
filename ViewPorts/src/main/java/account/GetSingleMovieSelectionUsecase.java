package account;

import modelDTO.AccountDTO;
import modelDTO.MovieRentalDTO;

public interface GetSingleMovieSelectionUsecase {
    AccountDTO getSingleMovieSelection(MovieRentalDTO m);
}
