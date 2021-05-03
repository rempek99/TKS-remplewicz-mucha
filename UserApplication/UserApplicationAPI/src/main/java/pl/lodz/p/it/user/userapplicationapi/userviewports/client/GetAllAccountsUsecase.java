package pl.lodz.p.it.user.userapplicationapi.userviewports.client;

import java.util.List;

public interface GetAllAccountsUsecase<T> {
    List<T> getAllAccounts();
}
