package pl.lodz.p.it.soap.api;

import pl.lodz.p.it.repositoriesadapters.model_ent.repositories.RepositoryException;
import pl.lodz.p.it.soap.aggregates.adapters.AccountSoapAdapter;
import pl.lodz.p.it.soap.model.AccountSoap;

import javax.inject.Inject;
import javax.jws.WebService;
import javax.ws.rs.POST;
import javax.xml.namespace.QName;
import javax.xml.rpc.soap.SOAPFaultException;
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
          throw new SoapException("Not found");
        }
    }

    public void updateSingleAccount(String str,AccountSoap desiredAccount) {
        AccountSoap accountToChange = accountAdapter.getAccountViaUUID(str);
        accountAdapter.updateSingleAccount(accountToChange, desiredAccount);
    }
}
