package pl.lodz.p.it.rentapplicationports.usecase.client;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Client;
import java.util.List;

public interface GetAllClientsUsecase {
    List<Client> getAll();
}
