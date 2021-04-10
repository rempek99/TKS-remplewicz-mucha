package pl.lodz.p.it.applicationports.usecase.account;

import pl.lodz.p.it.applicationcore.domainmodel.model.Account;
import java.util.List;

public interface GetAllAccountsUsecase {
    List<Account> getAll();
}
