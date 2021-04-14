package pl.lodz.p.it.soap.api;

import pl.lodz.p.it.repositoriesadapters.model_ent.repositories.RepositoryException;
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

    public AccountSoap getSingleAccFromStorage(String str) throws SoapException {
        try {
            return accountAdapter.getAccountViaUUID(str);
        }
        catch (IllegalArgumentException e) {
            if(e.getMessage().equals(SoapException.NOT_FOUND))
                throw new SoapException(SoapException.NOT_FOUND);
            else
                throw e;
        }
    }

    @POST
    public AccountSoap addSingleAccToStorage(AccountSoap account) throws SoapException {
        try {
            return accountAdapter.addAccount(account);
        }
        catch (IllegalArgumentException e)
        {
            if(e.getCause().getMessage().equals(RepositoryException.DUPLICATED)) {
                throw new SoapException("Duplicated");
            }
            else {
                throw e;
            }
        }

    }

    public String removeSingleAccFromStorage(String str) throws SoapException {
        Optional<AccountSoap> acc = Optional.ofNullable(accountAdapter.getAccountViaUUID(str));
        if(acc.isPresent()) {
            accountAdapter.removeAccount(acc.get());
            return SoapMessage.OK;
        }
        else{
          throw new SoapException(SoapException.NOT_FOUND);
        }
    }

    public void updateSingleAccount(String str,AccountSoap desiredAccount) throws SoapException {
        AccountSoap accountToChange = getSingleAccFromStorage(str);
        accountAdapter.updateSingleAccount(accountToChange, desiredAccount);
    }
}
