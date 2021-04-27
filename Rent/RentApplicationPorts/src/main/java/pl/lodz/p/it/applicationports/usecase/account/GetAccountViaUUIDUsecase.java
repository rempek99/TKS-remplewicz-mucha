package main.java.pl.lodz.p.it.applicationports.usecase.account;

import pl.lodz.p.it.applicationcore.domainmodel.model.Account;

import java.util.Optional;

public interface GetAccountViaUUIDUsecase {
    Optional<Account> getViaUUID(String str);
}
