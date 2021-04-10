package pl.lodz.p.it.viewports.account;

import pl.lodz.p.it.viewmodel.modelDTO.AccountDTO;
import java.util.List;

public interface GetAllAccountsUsecase {
    List<AccountDTO> getAllAccounts();
}
