package pl.lodz.p.it.viewports.account;

import pl.lodz.p.it.viewmodel.modelDTO.AccountDTO;

public interface UpdateSingleAccountUsecase {
    void updateSingleAccount(AccountDTO income, AccountDTO outcome);
}
