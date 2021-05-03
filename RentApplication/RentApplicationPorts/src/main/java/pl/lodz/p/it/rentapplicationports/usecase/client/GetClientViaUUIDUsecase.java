package pl.lodz.p.it.rentapplicationports.usecase.client;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Client;

import java.util.Optional;

public interface GetClientViaUUIDUsecase {
    Optional<Client> getViaUUID(String str);
}
