package pl.lodz.p.it.viewports.account;

public interface SetAccountStatusUsecase {
    void setAccountStatus(String id, boolean status, String role);
}
