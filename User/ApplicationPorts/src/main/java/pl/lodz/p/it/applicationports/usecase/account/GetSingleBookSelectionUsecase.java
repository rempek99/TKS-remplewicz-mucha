package pl.lodz.p.it.applicationports.usecase.account;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Account;
import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.BookRental;

public interface GetSingleBookSelectionUsecase {
    Account getSingleBookSelection(BookRental b);
}
