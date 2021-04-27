package pl.lodz.p.it.applicationports.infrastructure;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Account;
import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.BookRental;
import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.MovieRental;

import java.util.List;
import java.util.Optional;

public interface AccountPort {
    Account getAccount(Account a);
    Optional<Account> getAccountViaUUID(String str);
    List<Account> getAllAccounts();
    Account getMovieSelectedViaUUID(MovieRental m);
    Account getBookSelectedViaUUID(BookRental b);
    void updateSingleAcc(Account income, Account outcome);
    Account addAccount(Account a) throws IllegalArgumentException;
    void removeAccount(Account a);
}
