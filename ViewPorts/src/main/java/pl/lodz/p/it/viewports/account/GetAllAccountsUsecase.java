package pl.lodz.p.it.viewports.account;

import java.util.List;

public interface GetAllAccountsUsecase<T> {
    List<T> getAllAccounts();
}
