package pl.lodz.p.it.applicationports.usecase.account;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Account;
import java.util.List;

public interface GetAllAccountsUsecase {
    List<Account> getAll();
}
