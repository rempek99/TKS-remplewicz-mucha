package pl.lodz.p.it.rentapplicationports.usecase.client;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Account;

public interface UpdateSingleAccountUsecase {
    void update(Account income, Account outcome);
}
