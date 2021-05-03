package pl.lodz.p.it.rentrepositoriesadapters.aggregates.converters;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Client;
import pl.lodz.p.it.rentrepositoriesadapters.model_ent.entities.ClientEnt;

public class ClientConverter {

    private ClientConverter() {
    }

    public static ClientEnt convertClientToEnt(Client account){
        String firstName = account.getFirstName();
        String lastName = account.getLastName();
        String roleOfUser = account.getRoleOfUser();
        boolean isActive = account.isActive();
        String id = account.getId();
        String login = account.getLogin();
        String password = account.getPassword();

        ClientEnt newClient = new ClientEnt(firstName, lastName, roleOfUser, isActive, login, password);
        newClient.setId(id);

        return newClient;
    }

    public static Client convertEntToClient(ClientEnt account){
        String firstName = account.getFirstName();
        String lastName = account.getLastName();
        String roleOfUser = account.getRoleOfUser();
        boolean isActive = account.isActive();
        String id = account.getId();
        String login = account.getLogin();
        String password = account.getPassword();

        Client newClient = new Client(firstName, lastName, roleOfUser, isActive, login, password);
        newClient.setId(id);

        return newClient;
    }
}
