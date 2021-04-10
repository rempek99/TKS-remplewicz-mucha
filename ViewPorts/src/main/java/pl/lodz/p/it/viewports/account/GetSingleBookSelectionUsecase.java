package pl.lodz.p.it.viewports.account;

import pl.lodz.p.it.viewmodel.modelDTO.AccountDTO;
import pl.lodz.p.it.viewmodel.modelDTO.BookRentalDTO;

public interface GetSingleBookSelectionUsecase {
    AccountDTO getSingleBookSelection(BookRentalDTO b);
}
