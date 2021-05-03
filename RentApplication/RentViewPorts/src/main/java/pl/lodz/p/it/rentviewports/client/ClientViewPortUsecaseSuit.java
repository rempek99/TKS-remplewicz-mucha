package pl.lodz.p.it.rentviewports.client;

public interface ClientViewPortUsecaseSuit<A>
        extends
        AddClientUsecase<A>,
        GetClientUsecase<A>,
        GetClientViaUUIDUsecase<A>,
        GetAllClientsUsecase<A>,
        RemoveClientUsecase<A>,
        UpdateSingleClientUsecase<A>,
        SetClientStatusUsecase
{
}
