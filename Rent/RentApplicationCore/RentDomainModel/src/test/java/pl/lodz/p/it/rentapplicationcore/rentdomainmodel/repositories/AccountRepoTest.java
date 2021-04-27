package pl.lodz.p.it.rentapplicationcore.rentdomainmodel.repositories;


import pl.lodz.p.it.applicationcore.domainmodel.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        assertTrue(repo.getAll().isEmpty());
        repo.add(tester);
        repo.add(tester2);
        repo.add(tester3);
    }


    @Test
    void getAllAccounts() {
        assertFalse(repo.getAll().isEmpty());
        assertEquals(NUMBER_OF_TESTERS, repo.getAll().size());
    }

    @Test
    void addAccount() {
        final int repo_size = repo.getAll().size();
        Account my_acc = new Account("Arek", "Remplewicz", "user", true, "ar", "rem1234");
        repo.add(my_acc);
        assertEquals(repo_size + 1, repo.getAll().size());
    }

    @Test
    void removeAccount() {
        final int repo_size = repo.getAll().size();
        repo.remove(tester3);
        assertEquals(repo_size - 1, repo.getAll().size());
    }

    @Test
    void getAccount() {
        Account found_acc = repo.get(tester2);
        assertEquals(tester2, found_acc);
    }

    @Test
    void getAccountViaUUID() {
        Account my_acc = new Account("Arek", "Remplewicz", "user", true, "ar", "rem1234");
        Account added = repo.add(my_acc);
        Account found = repo.getViaUUID(added.getId());
        assertEquals(my_acc, found);
    }

    @Test
    void updateSingleAcc() {
        Account my_acc = new Account("Arek", "Remplewicz", "user", true, "ar", "rem1234");
        Account updated = repo.update(tester, my_acc);
        assertEquals(my_acc.getFirstName(), updated.getFirstName());
        assertNotEquals(my_acc.getId(), updated.getId());
    }
}