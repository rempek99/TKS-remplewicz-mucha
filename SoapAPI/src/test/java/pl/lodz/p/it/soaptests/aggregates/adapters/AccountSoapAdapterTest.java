package pl.lodz.p.it.soaptests.aggregates.adapters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.lodz.p.it.applicationcore.domainmodel.model.Account;
import pl.lodz.p.it.applicationcore.domainmodel.model.Movie;
import pl.lodz.p.it.applicationcore.domainmodel.model.MovieRental;
import pl.lodz.p.it.applicationports.usecase.account.AccountUsecaseSuit;
import pl.lodz.p.it.repositoriesadapters.aggregates.adapters.AccountRepoAdapter;
import pl.lodz.p.it.repositoriesadapters.aggregates.converters.MovieConverter;
import pl.lodz.p.it.repositoriesadapters.model_ent.entities.MovieEnt;
import pl.lodz.p.it.repositoriesadapters.model_ent.repositories.AccountEntRepo;
import pl.lodz.p.it.soap.aggregates.adapters.AccountSoapAdapter;
import pl.lodz.p.it.soap.model.AccountSoap;

import java.util.List;

class AccountSoapAdapterTest {

    AccountUsecaseSuit accountService;

    private final AccountSoapAdapter accountSoapAdapter = new AccountSoapAdapter(accountService);
    private final AccountSoap tester = new AccountSoap("Tester", "Testowy", "user", true, "test", "test123");
    private final AccountSoap tester2 = new AccountSoap("Tester2", "Testowy2", "user", true, "test2", "test1234");
    private final AccountSoap tester3 = new AccountSoap("Tester3", "Testowy3", "user", true, "test3", "test12345");
    private AccountSoap temporary;


    @BeforeEach
    void initadapter() {
        accountSoapAdapter.addAccount(tester);
        accountSoapAdapter.addAccount(tester2);
        accountSoapAdapter.addAccount(tester3);
    }

//    @Test
//    void getAllAccounts() {
//    }
//
//    @Test
//    void addAccount() {
//    }
//
//    @Test
//    void removeAccount() {
//    }
//
//    @Test
//    void getAccount() {
//    }
//
//    @Test
//    void getMovieSelectedViaUUID() {
//        List<Account> allAccounts = accountSoapAdapter.getAllAccounts();
//        System.out.println(allAccounts);
//        Movie testMovie = new Movie("Test", "Tester", 100,false);
//        MovieRental testRental = new MovieRental(testMovie, temporary);
//        MovieEnt testMovieEnt = MovieConverter.convertMovieToEnt(testMovie);
//        accountSoapAdapter.getMovieSelectedViaUUID(testRental);
//    }
//
//    @Test
//    void getBookSelectedViaUUID() {
//    }
//
//    @Test
//    void getAccountViaUUID() {
//    }
//
//    @Test
//    void updateSingleAcc() {
//    }
}