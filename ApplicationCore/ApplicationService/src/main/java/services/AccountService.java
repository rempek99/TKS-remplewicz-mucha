package services;

import infrastructure.AccountPort;
import model.*;
import usecase.account.AccountUsecaseSuit;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@SessionScoped
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
    public Account getSingleMovieSelection(MovieRental m) {
        return accountRepo.getMovieSelectedViaUUID(m);
    }

    @Override
    public Account getSingleBookSelection(BookRental b) {return accountRepo.getBookSelectedViaUUID(b); }

    @Override
    public Account getViaUUID(String str) {
        return accountRepo.getAccountViaUUID(str);
    }

    @Override
    public void update(Account income, Account outcome) {accountRepo.updateSingleAcc(income, outcome);}

    @Override
    public void add(Account a) {
        accountRepo.addAccount(a);
    }

    @Override
    public void remove(Account a) {
        accountRepo.removeAccount(a);
    }

    @Override
    public Account get(Account a) {
        return accountRepo.getAccount(a);
    }

    public void setAccountStatus(String id, boolean status, String role) {
        Account account = accountRepo.getAccountViaUUID(id);
        Account newAccount = new Account(account.getFirstName(),account.getLastName(),role,status,account.getLogin(),account.getPassword());
        accountRepo.updateSingleAcc(account,newAccount);
    }
}
