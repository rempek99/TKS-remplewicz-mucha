package pl.lodz.p.it.rentapplicationports.usecase.client;

public interface SetClientStatusUsecase {
    void setClientStatus(String id, boolean status, String role);
}
