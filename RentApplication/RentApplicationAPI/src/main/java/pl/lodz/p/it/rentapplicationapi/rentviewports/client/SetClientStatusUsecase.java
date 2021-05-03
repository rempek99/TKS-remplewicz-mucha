package pl.lodz.p.it.rentapplicationapi.rentviewports.client;

public interface SetClientStatusUsecase {
    void setClientStatus(String id, boolean status, String role);
}
