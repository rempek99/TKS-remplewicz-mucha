package pl.lodz.p.it.rentapplicationapi.aggregates.adapters;

import pl.lodz.p.it.rentapplicationapi.aggregates.converters.ClientConverter;
import pl.lodz.p.it.rentapplicationapi.exceptions.RestException;
import pl.lodz.p.it.rentapplicationapi.rentviewmodel.modelDTO.ClientDTO;
import pl.lodz.p.it.rentapplicationapi.rentviewports.client.ClientViewPortUsecaseSuit;
import pl.lodz.p.it.rentapplicationports.usecase.client.ClientUsecaseSuit;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Dependent
public class ClientServiceAdapter implements ClientViewPortUsecaseSuit<ClientDTO>, Serializable{

    ClientUsecaseSuit accountService;

    @Inject
    public ClientServiceAdapter(ClientUsecaseSuit accountService) {
        this.accountService = accountService;
    }


    @Override
    public List<ClientDTO> getAllClients(){
        return accountService
                .getAll()
                .stream()
                .map(ClientConverter::convertClientToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO addClient(ClientDTO a) throws Exception {
        if(a.getFirstName().equals("blad"))
            throw new Exception();
        return ClientConverter.convertClientToDTO(
                accountService.add(ClientConverter.convertDTOToClient(a))
        );
    }

    @Override
    public void removeClient(ClientDTO a) {
        accountService.remove(ClientConverter.convertDTOToClient(a));
    }

    @Override
    public ClientDTO getClient(ClientDTO a) {
        return ClientConverter.convertClientToDTO(
                accountService.get(ClientConverter.convertDTOToClient(a))
        );
    }

    @Override
    public ClientDTO getClientViaUUID(String str) {
        if(accountService.getViaUUID(str).isPresent()) {
            return ClientConverter.convertClientToDTO(accountService.getViaUUID(str).get());
        }
        else{
            throw new IllegalArgumentException(RestException.NOT_FOUND);
        }
    }

    @Override
    public void updateSingleClient(ClientDTO accToChange, ClientDTO accWithData) {
        accountService.update(ClientConverter.convertDTOToClient(accToChange), ClientConverter.convertDTOToClient(accWithData));
    }

    @Override
    public void setClientStatus(String id, boolean status, String role) {
        accountService.setClientStatus(id, status, role);
    }
}
