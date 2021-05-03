package pl.lodz.p.it.user.userapplicationports.client;


import pl.lodz.p.it.user.userapplicationcore.userdomainmodel.model.Account;

import java.util.Optional;

public interface GetAccountViaUUIDUsecase {
    Optional<Account> getViaUUID(String str);
}
