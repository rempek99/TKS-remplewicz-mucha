package pl.lodz.p.it.rentviewports.client;

public interface UpdateSingleAccountUsecase<T> {
    void updateSingleAccount(T income, T outcome);
}
