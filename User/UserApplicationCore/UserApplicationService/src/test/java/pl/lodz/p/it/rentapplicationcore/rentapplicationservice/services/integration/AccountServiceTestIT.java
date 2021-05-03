package pl.lodz.p.it.rentapplicationcore.rentapplicationservice.services.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import pl.lodz.p.it.user.userapplicationcore.userapplicationservice.services.AccountService;
import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.*;
import pl.lodz.p.it.rentrepositoriesadapters.aggregates.adapters.AccountRepoAdapter;
import pl.lodz.p.it.rentrepositoriesadapters.model_ent.repositories.AccountEntRepo;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AccountServiceTestIT {

    private AccountService accountService;

    private final Account tester = new Account("Tester", "Testowy", "user", true, "test", "test123");
    private final Account tester2 = new Account("Tester2", "Testowy2", "user", true, "test2", "test1234");
    private final Account tester3 = new Account("Tester3", "Testowy3", "user", true, "test3", "test12345");

    @BeforeEach
    void initServiceAndRepositories() {
        accountService = new AccountService(new AccountRepoAdapter(new AccountEntRepo()));
        accountService.add(tester);
    }

    @Test
    void getAllAccounts() {
        List<Account> accountList = accountService.getAll();
        assertFalse(accountList.isEmpty());
    }

    @Test
    void getAccountViaUUID() {
        Account exampleAccount = accountService.getAll().get(0);
        Account foundAccount = accountService.getViaUUID(exampleAccount.getId()).get();
        assertEquals(exampleAccount, foundAccount);
    }

    @Test
    void updateSingleAccount() {
        Account exampleAccount = accountService.getAll().get(0);
        accountService.update(exampleAccount, tester2);
        Account foundAccount = accountService.getViaUUID(exampleAccount.getId()).get();
        assertEquals(tester2.getFirstName(), foundAccount.getFirstName());
    }

    @Test
    void addAccount() {
        int size = accountService.getAll().size();
        accountService.add(tester2);
        assertEquals(size + 1, accountService.getAll().size());
    }

    @Test
    void removeAccount() {
        int size = accountService.getAll().size();
        Account exampleAccount = accountService.getAll().get(0);
        accountService.remove(exampleAccount);
        assertEquals(size - 1, accountService.getAll().size());
    }

    @Test
    void getAccount() {
        Account exampleAccount = accountService.getAll().get(0);
        Account exampleAccountByID = accountService.get(exampleAccount);
        assertEquals(exampleAccount, exampleAccountByID);
    }
}