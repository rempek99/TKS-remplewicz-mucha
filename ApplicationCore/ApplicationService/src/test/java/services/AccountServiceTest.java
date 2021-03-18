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
        accountService.addAccount(tester);
    }

    @Test
    void getAllAccounts() {
        List<Account> accountList = accountService.getAllAccounts();
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
        Account exampleAccount = accountService.getAllAccounts().get(0);
        Account foundAccount = accountService.getAccountViaUUID(exampleAccount.getId());
        assertEquals(exampleAccount, foundAccount);
    }

    @Test
    void updateSingleAccount() {
        Account exampleAccount = accountService.getAllAccounts().get(0);
        accountService.updateSingleAccount(exampleAccount, tester2);
        Account foundAccount = accountService.getAccountViaUUID(exampleAccount.getId());
        assertEquals(tester2.getFirstName(), foundAccount.getFirstName());
    }

    @Test
    void addAccount() {
        int size = accountService.getAllAccounts().size();
        accountService.addAccount(tester2);
        assertEquals(size + 1, accountService.getAllAccounts().size());
    }

    @Test
    void removeAccount() {
        int size = accountService.getAllAccounts().size();
        Account exampleAccount = accountService.getAllAccounts().get(0);
        accountService.removeAccount(exampleAccount);
        assertEquals(size - 1, accountService.getAllAccounts().size());
    }

    @Test
    void getAccount() {
        Account exampleAccount = accountService.getAllAccounts().get(0);
        Account exampleAccountByID = accountService.getAccount(exampleAccount);
        assertEquals(exampleAccount, exampleAccountByID);
    }
}