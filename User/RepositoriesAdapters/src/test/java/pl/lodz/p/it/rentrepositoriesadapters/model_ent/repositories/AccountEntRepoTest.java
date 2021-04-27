package pl.lodz.p.it.rentrepositoriesadapters.model_ent.repositories;


import pl.lodz.p.it.rentrepositoriesadapters.model_ent.entities.AccountEnt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountEntRepoTest {

    private static final int NUMBER_OF_TESTERS = 3;

    private AccountEntRepo repo;
    private final AccountEnt tester = new AccountEnt("Tester", "Testowy", "user", true, "test", "test123");
    private final AccountEnt tester2 = new AccountEnt("Tester2", "Testowy2", "user", true, "test2", "test1234");
    private final AccountEnt tester3 = new AccountEnt("Tester3", "Testowy3", "user", true, "test3", "test12345");

    @BeforeEach
    void initRepo() throws RepositoryException {
        repo = new AccountEntRepo();
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
    void addAccount() throws RepositoryException {
        final int repo_size = repo.getAll().size();
        AccountEnt my_acc = new AccountEnt("Arek", "Remplewicz", "user", true, "ar", "rem1234");
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
        AccountEnt found_acc = repo.get(tester2);
        assertEquals(tester2, found_acc);
    }
    @Test
    void getAccountViaUUID() throws RepositoryException {
        AccountEnt my_acc = new AccountEnt("Arek", "Remplewicz", "user", true, "ar", "rem1234");
        AccountEnt added = repo.add(my_acc);
        AccountEnt found = repo.getViaUUID(added.getId());
        assertEquals(my_acc, found);
    }

    @Test
    void updateSingleAcc() {
        AccountEnt my_acc = new AccountEnt("Arek", "Remplewicz", "user", true, "ar", "rem1234");
        AccountEnt updated = repo.update(tester, my_acc);
        assertEquals(my_acc.getFirstName(), updated.getFirstName());
        assertNotEquals(my_acc.getId(), updated.getId());
    }
}