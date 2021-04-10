package pl.lodz.p.it.applicationports.usecase.account;

public interface SetAccountStatusUsecase {
    void setAccountStatus(String id, boolean status, String role);
}
