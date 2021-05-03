package pl.lodz.p.it.user.userrepositories.aggregates.adapters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.lodz.p.it.user.userapplicationcore.userdomainmodel.model.Account;
import pl.lodz.p.it.user.userrepositories.model_ent.repositories.AccountEntRepo;


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
    void getAccountViaUUID() {
    }

    @Test
    void updateSingleAcc() {
    }
}