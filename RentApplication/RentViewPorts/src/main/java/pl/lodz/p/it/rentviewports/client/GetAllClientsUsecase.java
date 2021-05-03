package pl.lodz.p.it.rentviewports.client;

import java.util.List;

public interface GetAllClientsUsecase<T> {
    List<T> getAllClients();
}
