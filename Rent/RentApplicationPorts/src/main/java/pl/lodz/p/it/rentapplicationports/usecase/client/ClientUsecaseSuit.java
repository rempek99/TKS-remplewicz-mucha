package pl.lodz.p.it.rentapplicationports.usecase.client;

public interface ClientUsecaseSuit
        extends
        AddClientUsecase,
        GetClientUsecase,
        GetClientViaUUIDUsecase,
        GetAllClientsUsecase,
        GetSingleBookSelectionUsecase,
        GetSingleMovieSelectionUsecase,
        RemoveClientUsecase,
        UpdateSingleClientUsecase,
        SetClientStatusUsecase
{
}
