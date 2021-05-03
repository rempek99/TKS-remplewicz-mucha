package pl.lodz.p.it.rentapplicationapi.rentviewports.client;

public interface UpdateSingleClientUsecase<T> {
    void updateSingleClient(T income, T outcome);
}
