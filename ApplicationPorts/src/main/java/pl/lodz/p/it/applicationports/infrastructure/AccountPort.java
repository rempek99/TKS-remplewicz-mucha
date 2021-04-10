package pl.lodz.p.it.applicationports.infrastructure;

import pl.lodz.p.it.applicationcore.domainmodel.model.Account;
import pl.lodz.p.it.applicationcore.domainmodel.model.BookRental;
import pl.lodz.p.it.applicationcore.domainmodel.model.MovieRental;

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
