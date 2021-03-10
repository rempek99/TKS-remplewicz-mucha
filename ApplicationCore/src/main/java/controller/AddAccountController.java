package controller;

import model.Account;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class AddAccountController implements Serializable {

    @Inject
    private AccountService accountService;
    private Account account;

    @PostConstruct
    private void init() {
        account = new Account();
    }

    public Account getAccount() {
        return account;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public void addConfirmed() {
        accountService.addAccount(account);
        init();
    }

    public Account getSingleAccount(Account a) { return accountService.getAccount(a); }
}
