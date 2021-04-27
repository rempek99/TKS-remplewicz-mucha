package pl.lodz.p.it.rentapplicationports.usecase.client;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Client;

public interface UpdateSingleClientUsecase {
    void update(Client income, Client outcome);
}
