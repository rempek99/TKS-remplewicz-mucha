package pl.lodz.p.it.viewports.account;

public interface AccountViewPortUsecaseSuit<A,BR,MR>
        extends
        AddAccountUsecase<A>,
        GetAccountUsecase<A>,
        GetAccountViaUUIDUsecase<A>,
        GetAllAccountsUsecase<A>,
        GetSingleBookSelectionUsecase<A,BR>,
        GetSingleMovieSelectionUsecase<A,MR>,
        RemoveAccountUsecase<A>,
        UpdateSingleAccountUsecase<A>,
        SetAccountStatusUsecase
{
}
