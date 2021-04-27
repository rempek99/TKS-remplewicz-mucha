package main.java.pl.lodz.p.it.applicationports.usecase.account;

import pl.lodz.p.it.applicationcore.domainmodel.model.Account;
import pl.lodz.p.it.applicationcore.domainmodel.model.BookRental;

public interface GetSingleBookSelectionUsecase {
    Account getSingleBookSelection(BookRental b);
}
