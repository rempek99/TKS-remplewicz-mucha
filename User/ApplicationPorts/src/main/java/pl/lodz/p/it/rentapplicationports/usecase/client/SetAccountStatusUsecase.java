package pl.lodz.p.it.rentapplicationports.usecase.client;

public interface SetAccountStatusUsecase {
    void setAccountStatus(String id, boolean status, String role);
}
