package pl.lodz.p.it.rentapplicationports.usecase.client;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Account;

public interface AddAccountUsecase {
    Account add(Account a);
}
