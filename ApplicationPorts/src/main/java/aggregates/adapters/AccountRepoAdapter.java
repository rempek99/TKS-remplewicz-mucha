package aggregates.adapters;

import infrastructure.AccountPort;
import model.Account;
import model.Book;
import model.Movie;
import model_ent.entities.AccountEnt;
import model_ent.repositories.AccountEntRepo;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static aggregates.converters.AccountConverter.convertAccountToEnt;
import static aggregates.converters.AccountConverter.convertEntToAccount;

@Dependent
public class AccountRepoAdapter implements AccountPort, Serializable{

    private final AccountEntRepo accountRepo;
    private  List<AccountEnt> accounts;


    @Inject
    public AccountRepoAdapter(AccountEntRepo accountRepo) {
        this.accountRepo = accountRepo;
        accounts = accountRepo.getAllAccounts();
    }



    @Override
    public List<Account> getAllAccounts(){
        List<Account> temp = Collections.synchronizedList(new ArrayList<Account>());
        for (AccountEnt account: accounts) {
            temp.add(convertEntToAccount(account));
        }
        return temp;
    }

    @Override
    public Account addAccount(Account a) {
        accountRepo.addAccount(convertAccountToEnt(a));
        return a;
    }

    @Override
    public void removeAccount(Account a) {
        accountRepo.removeAccount(convertAccountToEnt(a));
    }

    @Override
    public Account getAccount(Account a) {
        if (accounts.contains(a)) {
            return a;
        } else {
            return null;
        }
    }

    @Override
    public Account getMovieSelectedViaUUID(Movie movie) {
        String compare = movie.getRentalUserUUID();
        for(AccountEnt acc: accounts) {
            if(acc.getId().equals(compare)){
                return convertEntToAccount(acc);
            }
        }
        return null;
    }

    @Override
    public Account getBookSelectedViaUUID(Book book) {
        String compare = book.getRentalUserUUID();
        for(AccountEnt acc: accounts) {
            if(acc.getId().equals(compare)){
                return convertEntToAccount(acc);
            }
        }
        return null;
    }

    @Override
    public Account getAccountViaUUID(String str) {
        for(AccountEnt acc: accounts) {
            if(acc.getId().equals(str)){
                return convertEntToAccount(acc);
            }
        }
        return null;
    }

    @Override
    public void updateSingleAcc(Account accToChange, Account accWithData) {
        Account fromRepo = getAccount(accToChange);
        fromRepo.setActive(accWithData.isActive());
        fromRepo.setFirstName(accWithData.getFirstName());
        fromRepo.setLastName(accWithData.getLastName());
        fromRepo.setLogin(accWithData.getLogin());
        fromRepo.setPassword(accWithData.getPassword());
        fromRepo.setRoleOfUser(accWithData.getRoleOfUser());
    }
}
