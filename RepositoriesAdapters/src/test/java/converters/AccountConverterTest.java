package converters;

import aggregates.converters.AccountConverter;
import model.Account;
import model_ent.entities.AccountEnt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountConverterTest {

    private final AccountEnt accountEnt = new AccountEnt("account", "Testowy", "user", true, "account", "account123");
    private final Account account = new Account("account", "Testowy", "user", true, "account", "account123");

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