package pl.lodz.p.it.rentapplicationcore.rentapplicationservice.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.lodz.p.it.rentapplicationports.infrastructure.ClientPort;
import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @InjectMocks
    private ClientService accountService;

    @Mock(name = "accountPort")
    private ClientPort accountPort;

    private final Client tester = new Client("Tester", "Testowy", "user", true, "test", "test123");
    private final Client tester2 = new Client("Tester2", "Testowy2", "user", true, "test2", "test1234");
    private final Client tester3 = new Client("Tester3", "Testowy3", "user", true, "test3", "test12345");
    private final Movie testMovie = new Movie("Test3", "Tester3", 300, true);
    private final Book testBook = new Book("Test", "Tester", 100, false);
    private final BookRental testBookRental = new BookRental(testBook, tester);
    private final MovieRental testMovieRental = new MovieRental(testMovie, tester);

    @Test
    void getAllClients() {
        //given
        //when
        List<Client> accountList = accountService.getAll();
        //then
        then(accountPort).should().getAllClients();
    }

    @Test
    void getSingleMovieSelection() {
        //given
        given(accountPort.getMovieSelectedViaUUID(testMovieRental)).willReturn(tester);
        //when
        Client found = accountService.getSingleMovieSelection(testMovieRental);
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
        Client found = accountService.getSingleBookSelection(testBookRental);
        //then
        then(accountPort).should().getBookSelectedViaUUID(testBookRental);
        then(accountPort).shouldHaveNoMoreInteractions();
        assertEquals(tester, found);
    }

    @Test
    void getClientViaUUID() {
        //given
        String testID = tester.getId();
        given(accountPort.getClientViaUUID(tester.getId())).willReturn(Optional.of(tester));
        //when
        Client foundClient = accountService.getViaUUID(testID).get();
        //then
        then(accountPort).should().getClientViaUUID(testID);
        then(accountPort).shouldHaveNoMoreInteractions();
        ;
    }

    @Test
    void updateSingleClient() {
        //given
        Client exampleClient = tester;
        //when
        accountService.update(any(), any());
        //then
        then(accountPort).should().updateSingleAcc(any(), any());
        then(accountPort).shouldHaveNoMoreInteractions();
    }

    @Test
    void addClient() {
        //given
        Client exampleClient = tester;
        //when
        accountService.add(exampleClient);
        //then
        then(accountPort).should().addClient(exampleClient);
        then(accountPort).shouldHaveNoMoreInteractions();
    }

    @Test
    void removeClient() {
        //given
        Client exampleClient = tester;
        //when
        accountService.remove(exampleClient);
        //then
        then(accountPort).should().removeClient(exampleClient);
        then(accountPort).shouldHaveNoMoreInteractions();
    }

    @Test
    void getClient() {
        //given
        Client exampleClient = tester;
        //when
        Client exampleClientByID = accountService.get(exampleClient);
        //then
        then(accountPort).should().getClient(exampleClient);
        then(accountPort).shouldHaveNoMoreInteractions();
    }
}