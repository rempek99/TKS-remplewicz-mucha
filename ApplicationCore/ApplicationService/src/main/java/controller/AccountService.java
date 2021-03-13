package controller;

import infrastructure.AccountPort;
import model.*;
import usecase.*;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

@Dependent
public class AccountService implements Serializable, AddAccountUsecase, GetAccountUsecase, GetAllAccountsUsecase,
        GetSingleBookSelectionUsecase, GetSingleMovieSelectionUsecase, GetAccountViaUUIDUsecase,
        UpdateSingleAccountUsecase, RemoveAccountUsecase {

    private AccountPort accountRepo;

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
}
