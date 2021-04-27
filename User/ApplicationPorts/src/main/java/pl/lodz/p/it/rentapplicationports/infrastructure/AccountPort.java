package pl.lodz.p.it.rentapplicationports.infrastructure;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountPort {
    Account getAccount(Account a);
    Optional<Account> getAccountViaUUID(String str);
    List<Account> getAllAccounts();
    void updateSingleAcc(Account income, Account outcome);
    Account addAccount(Account a) throws IllegalArgumentException;
    void removeAccount(Account a);
}
