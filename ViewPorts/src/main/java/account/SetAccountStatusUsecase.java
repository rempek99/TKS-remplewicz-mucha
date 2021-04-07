package account;

public interface SetAccountStatusUsecase {
    void setAccountStatus(String id, boolean status, String role);
}
