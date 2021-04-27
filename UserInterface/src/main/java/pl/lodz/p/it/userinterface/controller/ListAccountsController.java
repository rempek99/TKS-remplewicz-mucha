package pl.lodz.p.it.userinterface.controller;

import pl.lodz.p.it.rentviewports.client.*;
import pl.lodz.p.it.rentviewmodel.modelDTO.*;

import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ListAccountsController implements Serializable{
    @Inject
    private GetSingleBookSelectionUsecase<AccountDTO,BookRentalDTO> getSingleBookService;
    @Inject
    private GetSingleMovieSelectionUsecase<AccountDTO, MovieRentalDTO> getSingleMovieService;
    @Inject
    private GetAccountViaUUIDUsecase<AccountDTO> getAccountViaUUIDService;
    @Inject
    private GetAllAccountsUsecase<AccountDTO> getAllAccountsService;
    @Inject
    private UpdateSingleAccountUsecase<AccountDTO> updateAccountService;
    @Inject
    private RemoveAccountUsecase<AccountDTO> removeAccountService;
    @Inject
    private GetAccountUsecase<AccountDTO> getAccountService;
    @Inject
    private SetAccountStatusUsecase setAccountStatusService;
    private List<AccountDTO> filteredAccounts;
    private boolean chooseStatus;
    private String chooseRole;

    public List<AccountDTO> getFilteredAccounts() {
        return filteredAccounts;
    }

    public void setFilteredAccounts(List<AccountDTO> filteredAccounts) {
        this.filteredAccounts = filteredAccounts;
    }

    public AccountDTO getSelectedSingleMovieAccount(MovieRentalDTO m) {return getSingleMovieService.getSingleMovieSelection(m); }
    public AccountDTO getSelectedSingleBookAccount(BookRentalDTO b) {return getSingleBookService.getSingleBookSelection(b); }
    public AccountDTO getDesiredAccount(String str) {return getAccountViaUUIDService.getAccountViaUUID(str); }

    public List<AccountDTO> getAccounts() {
        return getAllAccountsService.getAllAccounts();
    }

    public void updateAccount(AccountDTO income, AccountDTO outcome) { updateAccountService.updateSingleAccount(income, outcome);}

    public void removeSelectedAccount(AccountDTO a) {
        removeAccountService.removeAccount(a);
    }

    public AccountDTO getSingleAccount(AccountDTO a) { return getAccountService.getAccount(a); }


    public void updateStatusandRole(AccountDTO account) {
        setAccountStatusService.setAccountStatus(account.getId(), chooseStatus, chooseRole);
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
