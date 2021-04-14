package pl.lodz.p.it.applicationports.usecase.account;

import pl.lodz.p.it.applicationcore.domainmodel.model.Account;

public interface AddAccountUsecase {
    Account add(Account a);
}
