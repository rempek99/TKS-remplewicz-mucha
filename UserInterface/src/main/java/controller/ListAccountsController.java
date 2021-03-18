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
    private boolean chooseStatus;
    private String chooseRole;

    public List<Account> getFilteredAccounts() {
        return filteredAccounts;
    }

    public void setFilteredAccounts(List<Account> filteredAccounts) {
        this.filteredAccounts = filteredAccounts;
    }

    public Account getSelectedSingleMovieAccount(MovieRental m) {return accountService.getSingleMovieSelection(m); }
    public Account getSelectedSingleBookAccount(BookRental b) {return accountService.getSingleBookSelection(b); }
    public Account getDesiredAccount(String str) {return accountService.getViaUUID(str); }

    public List<Account> getAccounts() {
        return accountService.getAll();
    }

    public void updateAccount(Account income, Account outcome) { accountService.update(income, outcome);}

    public void removeSelectedAccount(Account a) {
        accountService.remove(a);
    }

    public Account getSingleAccount(Account a) { return accountService.get(a); }


    public void updateStatusandRole(Account account) {
        accountService.setAccountStatus(account.getId(),chooseStatus, chooseRole);
    }

    public boolean getChooseStatus() {
        return chooseStatus;
    }

    public void setChooseStatus(boolean chooseStatus) {
        this.chooseStatus = chooseStatus;
    }

    public String getChooseRole() {
        return chooseRole;
    }

    public void setChooseRole(String chooseRole) {
        this.chooseRole = chooseRole;
    }
}
