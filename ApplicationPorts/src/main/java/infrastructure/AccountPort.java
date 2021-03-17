package infrastructure;

import model.*;

import java.util.List;

public interface AccountPort {
    Account getAccount(Account a);
    Account getAccountViaUUID(String str);
    List<Account> getAllAccounts();
    Account getMovieSelectedViaUUID(MovieRental m);
    Account getBookSelectedViaUUID(BookRental b);
    void updateSingleAcc(Account income, Account outcome);
    Account addAccount(Account a);
    void removeAccount(Account a);
}
