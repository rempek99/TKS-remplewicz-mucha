package pl.lodz.p.it.viewports.account;

import pl.lodz.p.it.viewmodel.modelDTO.AccountDTO;

public interface UpdateSingleAccountUsecase<T> {
    void updateSingleAccount(T income, T outcome);
}
