package pl.lodz.p.it.userinterface.soap;

import pl.lodz.p.it.viewadapters.adapters.AccountServiceAdapter;
import pl.lodz.p.it.viewmodel.modelDTO.AccountDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;
import java.util.List;

@WebService(serviceName = "AccountAPI")
public class AccountSoap {

    @Inject
    AccountServiceAdapter accountAdapter;


    public String hello() {
        return "Hello Soap";
    }
    public List<AccountDTO> getAccounts() {
        return accountAdapter.getAllAccounts();
    }
}
