package pl.lodz.p.it.rentviewports.client;

import java.util.List;

public interface GetAllAccountsUsecase<T> {
    List<T> getAllAccounts();
}
