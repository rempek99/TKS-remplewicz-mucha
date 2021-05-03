package pl.lodz.p.it.user.userapplicationports.client;

public interface AccountUsecaseSuit
        extends
        AddAccountUsecase,
        GetAccountUsecase,
        GetAccountViaUUIDUsecase,
        GetAllAccountsUsecase,
        RemoveAccountUsecase,
        UpdateSingleAccountUsecase,
        SetAccountStatusUsecase
{
}
