package account;

import modelDTO.AccountDTO;

public interface UpdateSingleAccountUsecase {
    void updateSingleAccount(AccountDTO income, AccountDTO outcome);
}
