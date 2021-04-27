package pl.lodz.p.it.userinterface.controller;

import pl.lodz.p.it.viewports.account.GetAccountUsecase;
import pl.lodz.p.it.viewmodel.modelDTO.AccountDTO;
import pl.lodz.p.it.rentapplicationcore.rentapplicationservice.services.AccountService;
import pl.lodz.p.it.viewports.account.AddAccountUsecase;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class AddAccountController implements Serializable {

    @Inject
    private AddAccountUsecase<AccountDTO> addAccountService;
    @Inject
    private GetAccountUsecase<AccountDTO> getAccountService;
    private AccountDTO accountDTO;

    @PostConstruct
    private void init() {
        accountDTO = new AccountDTO();
    }

    public AccountDTO getAccount() {
        return accountDTO;
    }

    public AddAccountUsecase getAccountService() {
        return addAccountService;
    }

    public void setAccountService(AccountService accountService) {
        this.addAccountService = addAccountService;
    }

    public void addConfirmed() {
        addAccountService.addAccount(accountDTO);
        init();
    }

    public AccountDTO getSingleAccount(AccountDTO a) { return getAccountService.getAccount(a); }
}
