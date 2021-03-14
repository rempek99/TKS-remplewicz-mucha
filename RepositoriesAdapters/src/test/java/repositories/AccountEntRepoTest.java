package repositories;


import model.entities.AccountEnt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountEntRepoTest {

    private static final int NUMBER_OF_TESTERS = 3;

    private AccountEntRepo repo;
    private final AccountEnt tester = new AccountEnt("Tester", "Testowy", "user", true, "test", "test123");
    private final AccountEnt tester2 = new AccountEnt("Tester2", "Testowy2", "user", true, "test2", "test1234");
    private final AccountEnt tester3 = new AccountEnt("Tester3", "Testowy3", "user", true, "test3", "test12345");

    @BeforeEach
    void initRepo() {
        repo = new AccountEntRepo();
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
        AccountEnt my_acc = new AccountEnt("Arek", "Remplewicz", "user", true, "ar", "rem1234");
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
        AccountEnt found_acc = repo.getAccount(tester2);
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
        AccountEnt my_acc = new AccountEnt("Arek", "Remplewicz", "user", true, "ar", "rem1234");
        AccountEnt added = repo.addAccount(my_acc);
        AccountEnt found = repo.getAccountViaUUID(added.getId());
        assertEquals(my_acc, found);
    }

    @Test
    void updateSingleAcc() {
        AccountEnt my_acc = new AccountEnt("Arek", "Remplewicz", "user", true, "ar", "rem1234");
        AccountEnt updated = repo.updateSingleAcc(tester, my_acc);
        assertEquals(my_acc.getFirstName(), updated.getFirstName());
        assertNotEquals(my_acc.getId(), updated.getId());
    }
}