package account;

import modelDTO.AccountDTO;
import java.util.List;

public interface GetAllAccountsUsecase {
    List<AccountDTO> getAllAccounts();
}
