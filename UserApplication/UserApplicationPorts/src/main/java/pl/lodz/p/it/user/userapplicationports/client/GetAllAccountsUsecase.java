package pl.lodz.p.it.user.userapplicationports.client;

import pl.lodz.p.it.user.userapplicationcore.userdomainmodel.model.Account;

import java.util.List;

public interface GetAllAccountsUsecase {
    List<Account> getAll();
}
