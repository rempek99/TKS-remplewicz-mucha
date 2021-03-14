package model.repositories;


import model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.AssertTrue;

import static org.junit.jupiter.api.Assertions.*;

class AccountRepoTest {

    private static final int NUMBER_OF_TESTERS = 3;

    private AccountRepo repo;
    private final Account tester = new Account("Tester", "Testowy", "user", true, "test", "test123");
    private final Account tester2 = new Account("Tester2", "Testowy2", "user", true, "test2", "test1234");
    private final Account tester3 = new Account("Tester3", "Testowy3", "user", true, "test3", "test12345");

    @BeforeEach
    void initRepo() {
        repo = new AccountRepo();
        assertTrue(repo.getAllAccounts().isEmpty());
        repo.addAccount(tester);
        repo.addAccount(tester2);
        repo.addAccount(tester3);
    }


    @Test
    void getAllAccounts() {
        assertFalse(repo.getAllAccounts().isEmpty());
        assertEquals(NUMBER_OF_TESTERS, repo.getAllAccounts().size());
    }

    @Test
    void addAccount() {
        final int repo_size = repo.getAllAccounts().size();
        Account my_acc = new Account("Arek", "Remplewicz", "user", true, "ar", "rem1234");
        repo.addAccount(my_acc);
        assertEquals(repo_size + 1, repo.getAllAccounts().size());
    }

    @Test
    void removeAccount() {
        final int repo_size = repo.getAllAccounts().size();
        repo.removeAccount(tester3);
        assertEquals(repo_size - 1, repo.getAllAccounts().size());
    }

    @Test
    void getAccount() {
        Account found_acc = repo.getAccount(tester2);
        assertEquals(tester2, found_acc);
    }

    @Test
    @Disabled
    void getMovieSelectedViaUUID() {
    }

    @Test
    @Disabled
    void getBookSelectedViaUUID() {
    }

    @Test
    void getAccountViaUUID() {
        Account my_acc = new Account("Arek", "Remplewicz", "user", true, "ar", "rem1234");
        Account added = repo.addAccount(my_acc);
        Account found = repo.getAccountViaUUID(added.getId());
        assertEquals(my_acc, found);
    }

    @Test
    void updateSingleAcc() {
        Account my_acc = new Account("Arek", "Remplewicz", "user", true, "ar", "rem1234");
        Account updated = repo.updateSingleAcc(tester, my_acc);
        assertEquals(my_acc.getFirstName(), updated.getFirstName());
        assertNotEquals(my_acc.getId(), updated.getId());
    }
}