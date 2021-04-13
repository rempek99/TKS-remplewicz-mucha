package pl.lodz.p.it.soap.api;

import pl.lodz.p.it.soap.aggregates.adapters.AccountSoapAdapter;
import pl.lodz.p.it.soap.model.AccountSoap;

import javax.inject.Inject;
import javax.jws.WebService;
import javax.ws.rs.POST;
import java.util.List;
import java.util.Optional;

@WebService(serviceName = "AccountAPI")
public class AccountSoapAPI {

    @Inject
    AccountSoapAdapter accountAdapter;


    public String hello() {
        return "Hello Soap";
    }
    public List<AccountSoap> getAccounts() {
        return accountAdapter.getAllAccounts();
    }

    public AccountSoap getSingleAccFromStorage(String str) {
        return accountAdapter.getAccountViaUUID(str);
    }

    @POST
    public void addSingleAccToStorage(AccountSoap account) {
        accountAdapter.addAccount(account);
    }

    public void removeSingleAccFromStorage(String str) {
        Optional<AccountSoap> acc = Optional.ofNullable(accountAdapter.getAccountViaUUID(str));
        if(acc.isPresent()) {
            accountAdapter.removeAccount(acc.get());
        }
        else{
          throw new IllegalArgumentException("Account not found");
        }
    }

    public void updateSingleAccount(String str,AccountSoap desiredAccount) {
        AccountSoap accountToChange = accountAdapter.getAccountViaUUID(str);
        accountAdapter.updateSingleAccount(accountToChange, desiredAccount);
    }
}
