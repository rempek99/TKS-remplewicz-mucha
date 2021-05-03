package pl.lodz.p.it.user.userapplicationports.client;


import pl.lodz.p.it.user.userapplicationcore.userdomainmodel.model.Account;

public interface RemoveAccountUsecase {
    void remove(Account a);
}
