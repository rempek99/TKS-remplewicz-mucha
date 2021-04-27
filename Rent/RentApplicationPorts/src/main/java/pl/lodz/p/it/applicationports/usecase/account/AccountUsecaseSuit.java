package main.java.pl.lodz.p.it.applicationports.usecase.account;

import pl.lodz.p.it.applicationports.usecase.account.*;

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
