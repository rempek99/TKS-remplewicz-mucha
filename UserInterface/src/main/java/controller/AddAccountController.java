package controller;

import dtoadapters.AccountDTOAdapter;
import dtomodel.*;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class AddAccountController implements Serializable {
    private AccountDTOAdapter accountDTOAdapter;
    private AccountDTO account;

    @PostConstruct
    private void init() {
        account = new AccountDTO();
    }

    public AccountDTO getAccount() {
        return accountDTOAdapter.getAccount(account);
    }

    public AccountDTOAdapter getAccountService() {
        return accountDTOAdapter;
    }

    public void setAccountService(AccountDTOAdapter accountDTOAdapter) {
        this.accountDTOAdapter = accountDTOAdapter;
    }

    public void addConfirmed() {
        accountDTOAdapter.addAccount(account);
        init();
    }

    public AccountDTO getSingleAccount(AccountDTO a) { return accountDTOAdapter.getAccount(a); }
}
