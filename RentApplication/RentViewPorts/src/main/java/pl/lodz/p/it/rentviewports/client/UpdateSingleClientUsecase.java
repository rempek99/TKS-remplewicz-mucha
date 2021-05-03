package pl.lodz.p.it.rentviewports.client;

public interface UpdateSingleClientUsecase<T> {
    void updateSingleClient(T income, T outcome);
}
