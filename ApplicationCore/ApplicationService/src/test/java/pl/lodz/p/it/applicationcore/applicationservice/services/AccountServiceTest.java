package pl.lodz.p.it.applicationcore.applicationservice.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.lodz.p.it.applicationcore.domainmodel.model.*;
import pl.lodz.p.it.applicationports.infrastructure.AccountPort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock(name = "accountPort")
    private AccountPort accountPort;

    private final Account tester = new Account("Tester", "Testowy", "user", true, "test", "test123");
    private final Account tester2 = new Account("Tester2", "Testowy2", "user", true, "test2", "test1234");
    private final Account tester3 = new Account("Tester3", "Testowy3", "user", true, "test3", "test12345");
    private final Movie testMovie = new Movie("Test3", "Tester3", 300, true);
    private final Book testBook = new Book("Test", "Tester", 100, false);
    private final BookRental testBookRental = new BookRental(testBook, tester);
    private final MovieRental testMovieRental = new MovieRental(testMovie, tester);

    @Test
    void getAllAccounts() {
        //given
        //when
        List<Account> accountList = accountService.getAll();
        //then
        then(accountPort).should().getAllAccounts();
    }

    @Test
    void getSingleMovieSelection() {
        //given
        given(accountPort.getMovieSelectedViaUUID(testMovieRental)).willReturn(tester);
        //when
        Account found = accountService.getSingleMovieSelection(testMovieRental);
        //then
        then(accountPort).should().getMovieSelectedViaUUID(testMovieRental);
        then(accountPort).shouldHaveNoMoreInteractions();
        assertEquals(tester, found);
    }

    @Test
    void getSingleBookSelection() {
        //given
        given(accountPort.getBookSelectedViaUUID(testBookRental)).willReturn(tester);
        //when
        Account found = accountService.getSingleBookSelection(testBookRental);
        //then
        then(accountPort).should().getBookSelectedViaUUID(testBookRental);
        then(accountPort).shouldHaveNoMoreInteractions();
        assertEquals(tester, found);
    }

    @Test
    void getAccountViaUUID() {
        //given
        String testID = tester.getId();
        given(accountPort.getAccountViaUUID(tester.getId())).willReturn(Optional.of(tester));
        //when
        Account foundAccount = accountService.getViaUUID(testID).get();
        //then
        then(accountPort).should().getAccountViaUUID(testID);
        then(accountPort).shouldHaveNoMoreInteractions();
        ;
    }

    @Test
    void updateSingleAccount() {
        //given
        Account exampleAccount = tester;
        //when
        accountService.update(any(), any());
        //then
        then(accountPort).should().updateSingleAcc(any(), any());
        then(accountPort).shouldHaveNoMoreInteractions();
    }

    @Test
    void addAccount() {
        //given
        Account exampleAccount = tester;
        //when
        accountService.add(exampleAccount);
        //then
        then(accountPort).should().addAccount(exampleAccount);
        then(accountPort).shouldHaveNoMoreInteractions();
    }

    @Test
    void removeAccount() {
        //given
        Account exampleAccount = tester;
        //when
        accountService.remove(exampleAccount);
        //then
        then(accountPort).should().removeAccount(exampleAccount);
        then(accountPort).shouldHaveNoMoreInteractions();
    }

    @Test
    void getAccount() {
        //given
        Account exampleAccount = tester;
        //when
        Account exampleAccountByID = accountService.get(exampleAccount);
        //then
        then(accountPort).should().getAccount(exampleAccount);
        then(accountPort).shouldHaveNoMoreInteractions();
    }
}