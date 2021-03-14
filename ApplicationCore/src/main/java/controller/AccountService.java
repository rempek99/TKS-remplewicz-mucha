package controller;

import model.Account;
import model.repositories.AccountRepo;
import model.Book;
import model.Movie;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@SessionScoped
public class AccountService implements Serializable{
    @Inject
    private AccountRepo accountRepo;

    public List<Account> getAllAccounts() {
        return accountRepo.getAllAccounts();
    }

    public Account getSingleMovieSelection(Movie m) {return accountRepo.getMovieSelectedViaUUID(m); }
    public Account getSingleBookSelection(Book b) {return accountRepo.getBookSelectedViaUUID(b); }
    public Account getAccountViaUUID(String str) {return accountRepo.getAccountViaUUID(str); }

    public void updateSingleAccount(Account income, Account outcome) {accountRepo.updateSingleAcc(income, outcome);}

    public void addAccount(Account a) {
        accountRepo.addAccount(a);
    }

    public void removeAccount(Account a) {
        accountRepo.removeAccount(a);
    }

    public Account getAccount(Account a) {
        return accountRepo.getAccount(a);
    }

    public AccountRepo getAccountRepo() {
        return accountRepo;
    }
}
