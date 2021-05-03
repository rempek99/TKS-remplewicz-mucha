package pl.lodz.p.it.rentapplicationcore.rentapplicationservice.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.lodz.p.it.rentapplicationports.infrastructure.AccountPort;
import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.*;
import pl.lodz.p.it.user.userapplicationcore.userapplicationservice.services.AccountService;

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

    @Test
    void getAllAccounts() {
        //given
        //when
        List<Account> accountList = accountService.getAll();
        //then
        then(accountPort).should().getAllAccounts();
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