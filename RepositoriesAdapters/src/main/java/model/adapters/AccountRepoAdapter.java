package model.adapters;

import model.entities.AccountEnt;
import model.entities.AccountRepoEnt;
import infrastructure.*;
import model.*;
import static model.converters.AccountConverter.convertAccountToEnt;
import static model.converters.AccountConverter.convertEntToAccount;

import javax.inject.Inject;
import java.util.*;

public class AccountRepoAdapter implements AccountPort {
    @Inject
    private AccountRepoEnt accountRepo;

    private List<AccountEnt> accounts = accountRepo.getAllAccounts();

    @Override
    public List<Account> getAllAccounts(){
        List<Account> temp = Collections.synchronizedList(new ArrayList<Account>());
        for (AccountEnt account: accounts) {
            temp.add(convertEntToAccount(account));
        }
        return temp;
    }

    @Override
    public void addAccount(Account a) {
        accountRepo.addAccount(convertAccountToEnt(a));
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
