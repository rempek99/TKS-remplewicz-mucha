package pl.lodz.p.it.user.userapplicationapi.userviewports.client;

public interface SetAccountStatusUsecase {
    void setAccountStatus(String id, boolean status, String role);
}
