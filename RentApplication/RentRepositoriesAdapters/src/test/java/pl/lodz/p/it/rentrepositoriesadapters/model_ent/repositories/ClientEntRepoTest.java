package pl.lodz.p.it.rentrepositoriesadapters.model_ent.repositories;


import pl.lodz.p.it.rentrepositoriesadapters.model_ent.entities.ClientEnt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientEntRepoTest {

    private static final int NUMBER_OF_TESTERS = 3;

    private ClientEntRepo repo;
    private final ClientEnt tester = new ClientEnt("Tester", "Testowy", "user", true, "test", "test123");
    private final ClientEnt tester2 = new ClientEnt("Tester2", "Testowy2", "user", true, "test2", "test1234");
    private final ClientEnt tester3 = new ClientEnt("Tester3", "Testowy3", "user", true, "test3", "test12345");

    @BeforeEach
    void initRepo() throws RepositoryException {
        repo = new ClientEntRepo();
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
    void addClient() throws RepositoryException {
        final int repo_size = repo.getAll().size();
        ClientEnt my_acc = new ClientEnt("Arek", "Remplewicz", "user", true, "ar", "rem1234");
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
        ClientEnt found_acc = repo.get(tester2);
        assertEquals(tester2, found_acc);
    }
    @Test
    void getClientViaUUID() throws RepositoryException {
        ClientEnt my_acc = new ClientEnt("Arek", "Remplewicz", "user", true, "ar", "rem1234");
        ClientEnt added = repo.add(my_acc);
        ClientEnt found = repo.getViaUUID(added.getId());
        assertEquals(my_acc, found);
    }

    @Test
    void updateSingleAcc() {
        ClientEnt my_acc = new ClientEnt("Arek", "Remplewicz", "user", true, "ar", "rem1234");
        ClientEnt updated = repo.update(tester, my_acc);
        assertEquals(my_acc.getFirstName(), updated.getFirstName());
        assertNotEquals(my_acc.getId(), updated.getId());
    }
}