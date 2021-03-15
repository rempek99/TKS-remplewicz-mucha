package infrastructure;

import model.Account;
import model.Book;
import model.Movie;

import java.util.List;

public interface AccountPort {
    Account getAccount(Account a);
    Account getAccountViaUUID(String str);
    List<Account> getAllAccounts();
    Account getMovieSelectedViaUUID(Movie m);
    Account getBookSelectedViaUUID(Book b);
    void updateSingleAcc(Account income, Account outcome);
    Account addAccount(Account a);
    void removeAccount(Account a);
}
