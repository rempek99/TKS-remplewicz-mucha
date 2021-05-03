package pl.lodz.p.it.user.userapplicationapi.userviewports.client;

public interface UpdateSingleAccountUsecase<T> {
    void updateSingleAccount(T income, T outcome);
}
