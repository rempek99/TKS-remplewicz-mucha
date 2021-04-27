package pl.lodz.p.it.rentapplicationports.usecase.client;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Account;
import java.util.List;

public interface GetAllAccountsUsecase {
    List<Account> getAll();
}
