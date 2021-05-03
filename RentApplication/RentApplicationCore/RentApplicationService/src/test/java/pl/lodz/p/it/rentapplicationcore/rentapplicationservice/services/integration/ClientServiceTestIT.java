package pl.lodz.p.it.rentapplicationcore.rentapplicationservice.services.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import pl.lodz.p.it.rentapplicationcore.rentapplicationservice.services.ClientService;
import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.*;
import pl.lodz.p.it.rentrepositoriesadapters.aggregates.adapters.ClientRepoAdapter;
import pl.lodz.p.it.rentrepositoriesadapters.model_ent.repositories.ClientEntRepo;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ClientServiceTestIT {

    private ClientService accountService;

    private final Client tester = new Client("Tester", "Testowy", "user", true, "test", "test123");
    private final Client tester2 = new Client("Tester2", "Testowy2", "user", true, "test2", "test1234");
    private final Client tester3 = new Client("Tester3", "Testowy3", "user", true, "test3", "test12345");
    private final Movie testMovie = new Movie("Test3", "Tester3", 300, true);
    private final Book testBook = new Book("Test", "Tester", 100, false);
    private final BookRental testBookRental = new BookRental(testBook, tester);
    private final MovieRental testMovieRental = new MovieRental(testMovie, tester);


    @BeforeEach
    void initServiceAndRepositories() {
        accountService = new ClientService(new ClientRepoAdapter(new ClientEntRepo()));
        accountService.add(tester);
    }

    @Test
    void getAllClients() {
        List<Client> accountList = accountService.getAll();
        assertFalse(accountList.isEmpty());
    }

    @Test
    void getSingleMovieSelection() {
        Client found = accountService.getSingleMovieSelection(testMovieRental);
        assertEquals(tester, found);
    }

    @Test
    void getSingleBookSelection() {
        Client found = accountService.getSingleBookSelection(testBookRental);
        assertEquals(tester, found);
    }

    @Test
    void getClientViaUUID() {
        Client exampleClient = accountService.getAll().get(0);
        Client foundClient = accountService.getViaUUID(exampleClient.getId()).get();
        assertEquals(exampleClient, foundClient);
    }

    @Test
    void updateSingleClient() {
        Client exampleClient = accountService.getAll().get(0);
        accountService.update(exampleClient, tester2);
        Client foundClient = accountService.getViaUUID(exampleClient.getId()).get();
        assertEquals(tester2.getFirstName(), foundClient.getFirstName());
    }

    @Test
    void addClient() {
        int size = accountService.getAll().size();
        accountService.add(tester2);
        assertEquals(size + 1, accountService.getAll().size());
    }

    @Test
    void removeClient() {
        int size = accountService.getAll().size();
        Client exampleClient = accountService.getAll().get(0);
        accountService.remove(exampleClient);
        assertEquals(size - 1, accountService.getAll().size());
    }

    @Test
    void getClient() {
        Client exampleClient = accountService.getAll().get(0);
        Client exampleClientByID = accountService.get(exampleClient);
        assertEquals(exampleClient, exampleClientByID);
    }
}