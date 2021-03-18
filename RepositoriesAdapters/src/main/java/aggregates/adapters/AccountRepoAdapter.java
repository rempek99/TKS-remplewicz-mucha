package aggregates.adapters;

import infrastructure.AccountPort;
import model.Account;
import model.BookRental;
import model.MovieRental;
import model_ent.entities.AccountEnt;
import model_ent.repositories.AccountEntRepo;

import javax.enterprise.context.Dependent;
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
        cacheData();
    }
    private void cacheData() {
        accounts = accountRepo.getAllAccounts();
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
        accountRepo.addAccount(convertAccountToEnt(a));
        return a;
    }

    @Override
    public void removeAccount(Account a) {
        accountRepo.removeAccount(convertAccountToEnt(a));
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
    public Account getAccountViaUUID(String str) {
        cacheData();
        for(AccountEnt acc: accounts) {
            if(acc.getId().equals(str)){
                return convertEntToAccount(acc);
            }
        }
        return null;
    }

    @Override
    public void updateSingleAcc(Account accToChange, Account accWithData) {
        accountRepo.updateSingleAcc(convertAccountToEnt(accToChange),convertAccountToEnt(accWithData));
        //        Account fromRepo = accountRepo;
//        fromRepo.setActive(accWithData.isActive());
//        fromRepo.setFirstName(accWithData.getFirstName());
//        fromRepo.setLastName(accWithData.getLastName());
//        fromRepo.setLogin(accWithData.getLogin());
//        fromRepo.setPassword(accWithData.getPassword());
//        fromRepo.setRoleOfUser(accWithData.getRoleOfUser());
    }
}
