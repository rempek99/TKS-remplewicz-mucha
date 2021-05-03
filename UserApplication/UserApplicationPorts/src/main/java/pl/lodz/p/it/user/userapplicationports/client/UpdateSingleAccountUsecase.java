package pl.lodz.p.it.user.userapplicationports.client;


import pl.lodz.p.it.user.userapplicationcore.userdomainmodel.model.Account;

public interface UpdateSingleAccountUsecase {
    void update(Account income, Account outcome);
}
