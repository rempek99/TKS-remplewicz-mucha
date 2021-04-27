package main.java.pl.lodz.p.it.applicationports.infrastructure;

import pl.lodz.p.it.applicationcore.domainmodel.model.Account;
import pl.lodz.p.it.applicationcore.domainmodel.model.BookRental;
import pl.lodz.p.it.applicationcore.domainmodel.model.MovieRental;

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
