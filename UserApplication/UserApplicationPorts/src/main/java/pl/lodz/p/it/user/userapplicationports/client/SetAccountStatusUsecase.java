package pl.lodz.p.it.user.userapplicationports.client;

public interface SetAccountStatusUsecase {
    void setAccountStatus(String id, boolean status, String role);
}
