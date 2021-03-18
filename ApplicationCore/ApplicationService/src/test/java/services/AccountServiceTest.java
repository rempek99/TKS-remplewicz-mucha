package services;

import model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class AccountServiceTest {

    private AccountService accountService;
    private final Account tester = new Account("Tester", "Testowy", "user", true, "test", "test123");
    private final Account tester2 = new Account("Tester2", "Testowy2", "user", true, "test2", "test1234");
    private final Account tester3 = new Account("Tester3", "Testowy3", "user", true, "test3", "test12345");

    @BeforeEach
    void initServiceAndRepositories() {
        //accountService = new AccountService(new AccountRepoAdapter(new AccountEntRepo()));
        accountService.add(tester);
    }

    @Test
    void getAllAccounts() {
        List<Account> accountList = accountService.getAll();
        assertFalse(accountList.isEmpty());
    }

    @Test
    @Disabled
    void getSingleMovieSelection() {
    }

    @Test
    @Disabled
    void getSingleBookSelection() {
    }

    @Test
    void getAccountViaUUID() {
        Account exampleAccount = accountService.getAll().get(0);
        Account foundAccount = accountService.getViaUUID(exampleAccount.getId());
        assertEquals(exampleAccount, foundAccount);
    }

    @Test
    void updateSingleAccount() {
        Account exampleAccount = accountService.getAll().get(0);
        accountService.update(exampleAccount, tester2);
        Account foundAccount = accountService.getViaUUID(exampleAccount.getId());
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