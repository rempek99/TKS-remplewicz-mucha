package pl.lodz.p.it.viewports.account;

public interface AccountUsecaseSuit
        extends
        AddAccountUsecase,
        GetAccountUsecase,
        GetAccountViaUUIDUsecase,
        GetAllAccountsUsecase,
        GetSingleBookSelectionUsecase,
        GetSingleMovieSelectionUsecase,
        RemoveAccountUsecase,
        UpdateSingleAccountUsecase,
        SetAccountStatusUsecase
{
}
