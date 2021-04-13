package pl.lodz.p.it.viewports.account;

import pl.lodz.p.it.viewmodel.modelDTO.AccountDTO;

public interface GetAccountUsecase<T> {
    T getAccount(T a);
}
