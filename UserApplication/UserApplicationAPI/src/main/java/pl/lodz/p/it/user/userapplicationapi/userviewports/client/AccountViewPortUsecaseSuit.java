package pl.lodz.p.it.user.userapplicationapi.userviewports.client;


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
