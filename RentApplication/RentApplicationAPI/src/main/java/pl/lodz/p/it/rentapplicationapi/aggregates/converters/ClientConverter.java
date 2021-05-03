package pl.lodz.p.it.rentapplicationapi.aggregates.converters;

import pl.lodz.p.it.rentapplicationapi.rentviewmodel.modelDTO.ClientDTO;
import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Client;

public class ClientConverter {

    private ClientConverter() {
    }

    public static ClientDTO convertClientToDTO(Client account){
        String firstName = account.getFirstName();
        String lastName = account.getLastName();
        String roleOfUser = account.getRoleOfUser();
        boolean isActive = account.isActive();
        String id = account.getId();
        String login = account.getLogin();
        String password = account.getPassword();

        ClientDTO newAccount = new ClientDTO(firstName, lastName, roleOfUser, isActive, login, password);
        newAccount.setId(id);

        return newAccount;
    }

    public static Client convertDTOToClient(ClientDTO account){
        String firstName = account.getFirstName();
        String lastName = account.getLastName();
        String roleOfUser = account.getRoleOfUser();
        boolean isActive = account.isActive();
        String id = account.getId();
        String login = account.getLogin();
        String password = account.getPassword();

        Client newAccount = new Client(firstName, lastName, roleOfUser, isActive, login, password);
        newAccount.setId(id);

        return newAccount;
    }
}