package pl.lodz.p.it.user.userapplicationapi.userviewports.client;

import pl.lodz.p.it.rentviewports.client.UpdateSingleAccountUsecase;

public interface AccountViewPortUsecaseSuit<A>
        extends
        AddAccountUsecase<A>,
        GetAccountUsecase<A>,
        GetAccountViaUUIDUsecase<A>,
        GetAllAccountsUsecase<A>,
        RemoveAccountUsecase<A>,
        UpdateSingleAccountUsecase<A>,
        SetAccountStatusUsecase
{
}
