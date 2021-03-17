package controller;

import model.*;
import services.AccountService;

import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ListAccountsController implements Serializable{
    @Inject
    private AccountService accountService;
    private List<Account> filteredAccounts;

    public List<Account> getFilteredAccounts() {
        return filteredAccounts;
    }

    public void setFilteredAccounts(List<Account> filteredAccounts) {
        this.filteredAccounts = filteredAccounts;
    }

    public Account getSelectedSingleMovieAccount(MovieRental m) {return accountService.getSingleMovieSelection(m); }
    public Account getSelectedSingleBookAccount(BookRental b) {return accountService.getSingleBookSelection(b); }
    public Account getDesiredAccount(String str) {return accountService.getAccountViaUUID(str); }

    public List<Account> getAccounts() {
        return accountService.getAllAccounts();
    }

    public void updateAccount(Account income, Account outcome) { accountService.updateSingleAccount(income, outcome);}

    public void removeSelectedAccount(Account a) {
        accountService.removeAccount(a);
    }

    public Account getSingleAccount(Account a) { return accountService.getAccount(a); }


}
