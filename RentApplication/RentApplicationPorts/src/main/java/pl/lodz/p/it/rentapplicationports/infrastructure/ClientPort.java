package pl.lodz.p.it.rentapplicationports.infrastructure;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.*;

import java.util.List;
import java.util.Optional;

public interface ClientPort {
    Client getClient(Client a);
    Optional<Client> getClientViaUUID(String str);
    List<Client> getAllClients();
    Client getMovieSelectedViaUUID(MovieRental m);
    Client getBookSelectedViaUUID(BookRental b);
    void updateSingleAcc(Client income, Client outcome);
    Client addClient(Client a) throws IllegalArgumentException;
    void removeClient(Client a);
}
