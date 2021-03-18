package usecase.account;

import model.Account;
import model.Book;
import model.BookRental;

public interface GetSingleBookSelectionUsecase {
    Account getSingleBookSelection(BookRental b);
}
