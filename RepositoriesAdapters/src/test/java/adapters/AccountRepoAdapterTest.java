package adapters;

import aggregates.adapters.AccountRepoAdapter;
import aggregates.converters.MovieConverter;
import model.Account;
import model.Movie;
import model.MovieRental;
import model_ent.entities.MovieEnt;
import model_ent.repositories.AccountEntRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class AccountRepoAdapterTest {

    private final AccountRepoAdapter accountRepoAdapter = new AccountRepoAdapter(new AccountEntRepo());
    private final Account tester = new Account("Tester", "Testowy", "user", true, "test", "test123");
    private final Account tester2 = new Account("Tester2", "Testowy2", "user", true, "test2", "test1234");
    private final Account tester3 = new Account("Tester3", "Testowy3", "user", true, "test3", "test12345");
    private Account temporary;


    @BeforeEach
    void initadapter() {
        temporary = accountRepoAdapter.addAccount(tester);
        accountRepoAdapter.addAccount(tester2);
        accountRepoAdapter.addAccount(tester3);
    }

    @Test
    void getAllAccounts() {
    }

    @Test
    void addAccount() {
    }

    @Test
    void removeAccount() {
    }

    @Test
    void getAccount() {
    }

    @Test
    void getMovieSelectedViaUUID() {
        List<Account> allAccounts = accountRepoAdapter.getAllAccounts();
        System.out.println(allAccounts);
        Movie testMovie = new Movie("Test", "Tester", 100,false);
        MovieRental testRental = new MovieRental(testMovie, temporary);
        MovieEnt testMovieEnt = MovieConverter.convertMovieToEnt(testMovie);
        accountRepoAdapter.getMovieSelectedViaUUID(testRental);
    }

    @Test
    void getBookSelectedViaUUID() {
    }

    @Test
    void getAccountViaUUID() {
    }

    @Test
    void updateSingleAcc() {
    }
}