package usecase;

import model.Account;
import java.util.List;

public interface GetAllAccountsUsecase {
    List<Account> getAllAccounts();
}