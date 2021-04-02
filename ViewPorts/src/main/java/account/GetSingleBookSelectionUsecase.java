package account;

import modelDTO.AccountDTO;
import modelDTO.BookRentalDTO;

public interface GetSingleBookSelectionUsecase {
    AccountDTO getSingleBookSelection(BookRentalDTO b);
}
