package pl.lodz.p.it.soaptests.aggregates.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.lodz.p.it.applicationcore.domainmodel.model.Account;
import pl.lodz.p.it.soap.aggregates.converters.AccountSoapConverter;
import pl.lodz.p.it.soap.model.AccountSoap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountSoapConverterTest {

    private final AccountSoap accountSoap = new AccountSoap("pl.lodz.p.it.viewports.account", "Testowy", "user", true, "pl.lodz.p.it.viewports.account", "account123");
    private final Account account = new Account("pl.lodz.p.it.viewports.account", "Testowy", "user", true, "pl.lodz.p.it.viewports.account", "account123");

    @BeforeEach
    void init() {
        accountSoap.setId("212c5de0-ad07-444a-9100-fd422b4bff93");
        account.setId("212c5de0-ad07-444a-9100-fd422b4bff93");
    }

    @Test
    void convertAccountToEnt() {
        AccountSoap check = AccountSoapConverter.convertAccountToAccountSoap(account);
        assertEquals(check, accountSoap);
    }

    @Test
    void convertEntToAccount() {
        Account check = AccountSoapConverter.convertAccountSoapToAccount(accountSoap);
        assertEquals(check, account);
    }
}