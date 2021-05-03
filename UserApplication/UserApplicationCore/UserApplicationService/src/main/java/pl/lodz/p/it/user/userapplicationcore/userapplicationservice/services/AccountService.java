package pl.lodz.p.it.user.userapplicationcore.userapplicationservice.services;


import pl.lodz.p.it.user.userapplicationcore.userdomainmodel.model.Account;
import pl.lodz.p.it.user.userapplicationports.client.AccountUsecaseSuit;
import pl.lodz.p.it.user.userapplicationports.infrastructure.AccountPort;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Dependent
public class AccountService implements Serializable, AccountUsecaseSuit, IService<Account> {


    private AccountPort accountRepo;

    @Inject
    public AccountService(AccountPort accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public List<Account> getAll() {
        return accountRepo.getAllAccounts();
    }

    @Override
    public Optional<Account> getViaUUID(String str) {
        return accountRepo.getAccountViaUUID(str);
    }

    @Override
    public void update(Account income, Account outcome) {accountRepo.updateSingleAcc(income, outcome);}

    @Override
    public Account add(Account a) throws IllegalArgumentException {
        return accountRepo.addAccount(a);
    }

    @Override
    public void remove(Account a) {
        accountRepo.removeAccount(a);
    }

    @Override
    public Account get(Account a) {
        return accountRepo.getAccount(a);
    }

    @Override
    public void setAccountStatus(String id, boolean status, String role) {
        Optional<Account> accountOpt = accountRepo.getAccountViaUUID(id);
        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();
            Account newAccount = new Account(account.getFirstName(), account.getLastName(), role, status, account.getLogin(), account.getPassword());
            accountRepo.updateSingleAcc(account, newAccount);
        }
        else {
            throw new IllegalArgumentException("Not found");
        }
    }
}
