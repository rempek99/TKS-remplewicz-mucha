package pl.lodz.p.it.viewports.account;

import pl.lodz.p.it.viewmodel.modelDTO.AccountDTO;

public interface GetAccountViaUUIDUsecase<T> {
    T getAccountViaUUID(String str);
}
