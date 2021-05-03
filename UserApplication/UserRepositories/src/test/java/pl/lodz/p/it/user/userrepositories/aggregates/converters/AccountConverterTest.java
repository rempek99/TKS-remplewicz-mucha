package pl.lodz.p.it.user.userrepositories.aggregates.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.lodz.p.it.user.userapplicationcore.userdomainmodel.model.Account;
import pl.lodz.p.it.user.userrepositories.model_ent.entities.AccountEnt;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AccountConverterTest {

    private final AccountEnt accountEnt = new AccountEnt("pl.lodz.p.it.viewports.pl.lodz.p.it.rentviewports.account", "Testowy", "user", true, "pl.lodz.p.it.viewports.pl.lodz.p.it.rentviewports.account", "account123");
    private final Account account = new Account("pl.lodz.p.it.viewports.pl.lodz.p.it.rentviewports.account", "Testowy", "user", true, "pl.lodz.p.it.viewports.pl.lodz.p.it.rentviewports.account", "account123");

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