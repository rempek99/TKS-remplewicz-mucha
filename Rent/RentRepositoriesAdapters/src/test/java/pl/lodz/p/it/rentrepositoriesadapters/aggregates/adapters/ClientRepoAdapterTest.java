package pl.lodz.p.it.rentrepositoriesadapters.aggregates.adapters;

import pl.lodz.p.it.rentrepositoriesadapters.aggregates.converters.MovieConverter;
import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.*;
import pl.lodz.p.it.rentrepositoriesadapters.model_ent.entities.MovieEnt;
import pl.lodz.p.it.rentrepositoriesadapters.model_ent.repositories.ClientEntRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class ClientRepoAdapterTest {

    private final ClientRepoAdapter accountRepoAdapter = new ClientRepoAdapter(new ClientEntRepo());
    private final Client tester = new Client("Tester", "Testowy", "user", true, "test", "test123");
    private final Client tester2 = new Client("Tester2", "Testowy2", "user", true, "test2", "test1234");
    private final Client tester3 = new Client("Tester3", "Testowy3", "user", true, "test3", "test12345");
    private Client temporary;


    @BeforeEach
    void initadapter() {
        temporary = accountRepoAdapter.addClient(tester);
        accountRepoAdapter.addClient(tester2);
        accountRepoAdapter.addClient(tester3);
    }

    @Test
    void getAllClients() {
    }

    @Test
    void addClient() {
    }

    @Test
    void removeClient() {
    }

    @Test
    void getClient() {
    }

    @Test
    void getMovieSelectedViaUUID() {
        List<Client> allClients = accountRepoAdapter.getAllClients();
        System.out.println(allClients);
        Movie testMovie = new Movie("Test", "Tester", 100,false);
        MovieRental testRental = new MovieRental(testMovie, temporary);
        MovieEnt testMovieEnt = MovieConverter.convertMovieToEnt(testMovie);
        accountRepoAdapter.getMovieSelectedViaUUID(testRental);
    }

    @Test
    void getBookSelectedViaUUID() {
    }

    @Test
    void getClientViaUUID() {
    }

    @Test
    void updateSingleAcc() {
    }
}