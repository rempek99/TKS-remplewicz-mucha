package pl.lodz.p.it.applicationports.usecase.account;

import pl.lodz.p.it.applicationcore.domainmodel.model.Account;

public interface UpdateSingleAccountUsecase {
    void update(Account income, Account outcome);
}
