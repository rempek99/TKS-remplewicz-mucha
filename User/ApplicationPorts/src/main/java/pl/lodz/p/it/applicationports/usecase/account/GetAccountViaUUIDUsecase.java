package pl.lodz.p.it.applicationports.usecase.account;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Account;

import java.util.Optional;

public interface GetAccountViaUUIDUsecase {
    Optional<Account> getViaUUID(String str);
}
