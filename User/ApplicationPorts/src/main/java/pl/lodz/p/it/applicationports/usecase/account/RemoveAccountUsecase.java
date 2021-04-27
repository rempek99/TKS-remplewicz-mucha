package pl.lodz.p.it.applicationports.usecase.account;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Account;

public interface RemoveAccountUsecase {
    void remove(Account a);
}
