package pl.lodz.p.it.rentrepositoriesadapters.aggregates.converters;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Account;
import pl.lodz.p.it.rentrepositoriesadapters.model_ent.entities.AccountEnt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountConverterTest {

    private final AccountEnt accountEnt = new AccountEnt("pl.lodz.p.it.viewports.account", "Testowy", "user", true, "pl.lodz.p.it.viewports.account", "account123");
    private final Account account = new Account("pl.lodz.p.it.viewports.account", "Testowy", "user", true, "pl.lodz.p.it.viewports.account", "account123");

    @BeforeEach
    void init() {
        accountEnt.setId("212c5de0-ad07-444a-9100-fd422b4bff93");
        account.setId("212c5de0-ad07-444a-9100-fd422b4bff93");
    }

    @Test
    void convertAccountToEnt() {
        AccountEnt check = AccountConverter.convertAccountToEnt(account);
        assertEquals(check, accountEnt);
    }

    @Test
    void convertEntToAccount() {
        Account check = AccountConverter.convertEntToAccount(accountEnt);
        assertEquals(check, account);
    }
}