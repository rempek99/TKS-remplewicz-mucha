package controller;

import dtoadapters.*;
import dtomodel.*;
import model.*;

import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ListAccountsController implements Serializable{
    private AccountDTOAdapter accountDTOAdapter;
    private List<AccountDTO> filteredAccounts;

    public List<AccountDTO> getFilteredAccounts() {
        return filteredAccounts;
    }

    public void setFilteredAccounts(List<AccountDTO> filteredAccounts) {
        this.filteredAccounts = filteredAccounts;
    }

    public AccountDTO getSelectedSingleMovieAccount(Movie m) {return accountDTOAdapter.getMovieSelectedViaUUID(m); }
    public AccountDTO getSelectedSingleBookAccount(Book b) {return accountDTOAdapter.getBookSelectedViaUUID(b); }
    public AccountDTO getDesiredAccount(String str) {return accountDTOAdapter.getAccountViaUUID(str); }

    public List<AccountDTO> getAccounts() {
        return accountDTOAdapter.getAllAccounts();
    }

    public void updateAccount(AccountDTO income, AccountDTO outcome) { accountDTOAdapter.updateSingleAcc(income, outcome);}

    public void removeSelectedAccount(AccountDTO a) {
        accountDTOAdapter.removeAccount(a);
    }

    public AccountDTO getSingleAccount(AccountDTO a) { return accountDTOAdapter.getAccount(a); }
}
