package pl.lodz.p.it.user.userapplicationapi.aggregates.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.lodz.p.it.user.userapplicationapi.modelDTO.AccountDTO;
import pl.lodz.p.it.user.userapplicationcore.userdomainmodel.model.Account;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountRestConverterTest {

    private final AccountDTO accountDTO = new AccountDTO("pl.lodz.p.it.viewports.pl.lodz.p.it.rentviewports.account", "Testowy", "user", true, "pl.lodz.p.it.viewports.pl.lodz.p.it.rentviewports.account", "account123");
    private final Account account = new Account("pl.lodz.p.it.viewports.pl.lodz.p.it.rentviewports.account", "Testowy", "user", true, "pl.lodz.p.it.viewports.pl.lodz.p.it.rentviewports.account", "account123");

    @BeforeEach
    void init() {
        accountDTO.setId("212c5de0-ad07-444a-9100-fd422b4bff93");
        account.setId("212c5de0-ad07-444a-9100-fd422b4bff93");
    }

    @Test
    void convertAccountToEnt() {
        AccountDTO check = AccountConverter.convertAccountToDTO(account);
        assertEquals(check, accountDTO);
    }

    @Test
    void convertEntToAccount() {
        Account check = AccountConverter.convertDTOToAccount(accountDTO);
        assertEquals(check, account);
    }
}