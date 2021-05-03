package pl.lodz.p.it.rentapplicationapi.rentviewports.client;

import java.util.List;

public interface GetAllClientsUsecase<T> {
    List<T> getAllClients();
}
