package usecase.account;

import model.Account;
import model.Movie;
import model.MovieRental;

public interface GetSingleMovieSelectionUsecase {
    Account getSingleMovieSelection(MovieRental m);
}
