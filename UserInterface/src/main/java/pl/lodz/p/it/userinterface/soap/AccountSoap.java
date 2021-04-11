package pl.lodz.p.it.userinterface.soap;

import pl.lodz.p.it.viewadapters.adapters.AccountServiceAdapter;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;

@WebService(serviceName = "AccountAPI")
public class AccountSoap {

    @Inject
    AccountServiceAdapter accountAdapter;

    public String hello() {
        return "Hello Soap";
    }
}
