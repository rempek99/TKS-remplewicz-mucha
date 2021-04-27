package pl.lodz.p.it.rentapplicationcore.rentdomainmodel.repositories;


import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Client;

import static org.junit.jupiter.api.Assertions.*;

class ClientRepoTest {

    private static final int NUMBER_OF_TESTERS = 3;

    private ClientRepo repo;
    private final Client tester = new Client("Tester", "Testowy", "user", true, "test", "test123");
    private final Client tester2 = new Client("Tester2", "Testowy2", "user", true, "test2", "test1234");
    private final Client tester3 = new Client("Tester3", "Testowy3", "user", true, "test3", "test12345");

    @BeforeEach
    void initRepo() {
        repo = new ClientRepo();
        assertTrue(repo.getAll().isEmpty());
        repo.add(tester);
        repo.add(tester2);
        repo.add(tester3);
    }


    @Test
    void getAllClients() {
        assertFalse(repo.getAll().isEmpty());
        assertEquals(NUMBER_OF_TESTERS, repo.getAll().size());
    }

    @Test
    void addClient() {
        final int repo_size = repo.getAll().size();
        Client my_acc = new Client("Arek", "Remplewicz", "user", true, "ar", "rem1234");
        repo.add(my_acc);
        assertEquals(repo_size + 1, repo.getAll().size());
    }

    @Test
    void removeClient() {
        final int repo_size = repo.getAll().size();
        repo.remove(tester3);
        assertEquals(repo_size - 1, repo.getAll().size());
    }

    @Test
    void getClient() {
        Client found_acc = repo.get(tester2);
        assertEquals(tester2, found_acc);
    }

    @Test
    void getClientViaUUID() {
        Client my_acc = new Client("Arek", "Remplewicz", "user", true, "ar", "rem1234");
        Client added = repo.add(my_acc);
        Client found = repo.getViaUUID(added.getId());
        assertEquals(my_acc, found);
    }

    @Test
    void updateSingleAcc() {
        Client my_acc = new Client("Arek", "Remplewicz", "user", true, "ar", "rem1234");
        Client updated = repo.update(tester, my_acc);
        assertEquals(my_acc.getFirstName(), updated.getFirstName());
        assertNotEquals(my_acc.getId(), updated.getId());
    }
}