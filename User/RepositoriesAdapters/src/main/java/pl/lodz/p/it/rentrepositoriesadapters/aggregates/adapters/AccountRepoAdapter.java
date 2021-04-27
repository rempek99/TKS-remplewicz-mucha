package pl.lodz.p.it.rentrepositoriesadapters.aggregates.adapters;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Account;
import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.BookRental;
import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.MovieRental;
import pl.lodz.p.it.rentrepositoriesadapters.aggregates.converters.AccountConverter;
import pl.lodz.p.it.rentrepositoriesadapters.model_ent.entities.AccountEnt;
import pl.lodz.p.it.rentrepositoriesadapters.model_ent.repositories.AccountEntRepo;
import pl.lodz.p.it.applicationports.infrastructure.AccountPort;
import pl.lodz.p.it.rentrepositoriesadapters.model_ent.repositories.RepositoryException;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static pl.lodz.p.it.rentrepositoriesadapters.aggregates.converters.AccountConverter.convertAccountToEnt;
import static pl.lodz.p.it.rentrepositoriesadapters.aggregates.converters.AccountConverter.convertEntToAccount;

@Dependent
public class AccountRepoAdapter implements AccountPort, Serializable{

    private final AccountEntRepo accountRepo;
    private  List<AccountEnt> accounts;


    @Inject
    public AccountRepoAdapter(AccountEntRepo accountRepo) {
        this.accountRepo = accountRepo;
        cacheData();
    }
    private void cacheData() {
        accounts = accountRepo.getAll();
    }



    @Override
    public List<Account> getAllAccounts(){
        List<Account> temp = Collections.synchronizedList(new ArrayList<Account>());
        cacheData();
        for (AccountEnt account: accounts) {
            temp.add(convertEntToAccount(account));
        }
        return temp;
    }

    @Override
    public Account addAccount(Account a) {
        try {
            return convertEntToAccount(accountRepo.add(convertAccountToEnt(a)));
        } catch (RepositoryException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void removeAccount(Account a) {
        accountRepo.remove(convertAccountToEnt(a));
    }

    @Override
    public Account getAccount(Account a) {
        cacheData();
        if (accounts.contains(convertAccountToEnt(a))) {
            return a;
        } else {
            return null;
        }
    }

    @Override
    public Account getMovieSelectedViaUUID(MovieRental movieRental) {
        return movieRental.getAccount();
    }

    @Override
    public Account getBookSelectedViaUUID(BookRental bookRental) {
        return bookRental.getAccount();
    }

    @Override
    public Optional<Account> getAccountViaUUID(String str) {
        cacheData();
        return accounts
                .stream()
                .map(AccountConverter::convertEntToAccount)
                .filter(
                        account -> account.getId().equals(str)
                )
                .findFirst();
    }

    @Override
    public void updateSingleAcc(Account accToChange, Account accWithData) {
        accountRepo.update(convertAccountToEnt(accToChange),convertAccountToEnt(accWithData));
    }
}
