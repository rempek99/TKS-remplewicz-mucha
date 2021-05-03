package pl.lodz.p.it.rentapplicationapi.rentviewports.client;

public interface GetClientViaUUIDUsecase<T> {
    T getClientViaUUID(String str);
}
