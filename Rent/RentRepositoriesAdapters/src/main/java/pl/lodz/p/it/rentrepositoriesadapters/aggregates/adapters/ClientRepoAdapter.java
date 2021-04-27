package pl.lodz.p.it.rentrepositoriesadapters.aggregates.adapters;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.*;
import pl.lodz.p.it.rentapplicationports.infrastructure.ClientPort;
import pl.lodz.p.it.rentrepositoriesadapters.aggregates.converters.ClientConverter;
import pl.lodz.p.it.rentrepositoriesadapters.model_ent.entities.ClientEnt;
import pl.lodz.p.it.rentrepositoriesadapters.model_ent.repositories.ClientEntRepo;
import pl.lodz.p.it.rentrepositoriesadapters.model_ent.repositories.RepositoryException;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static pl.lodz.p.it.rentrepositoriesadapters.aggregates.converters.ClientConverter.convertClientToEnt;
import static pl.lodz.p.it.rentrepositoriesadapters.aggregates.converters.ClientConverter.convertEntToClient;

@Dependent
public class ClientRepoAdapter implements ClientPort, Serializable{

    private final ClientEntRepo accountRepo;
    private List<ClientEnt> accounts;


    @Inject
    public ClientRepoAdapter(ClientEntRepo accountRepo) {
        this.accountRepo = accountRepo;
        cacheData();
    }
    private void cacheData() {
        accounts = accountRepo.getAll();
    }



    @Override
    public List<Client> getAllClients(){
        List<Client> temp = Collections.synchronizedList(new ArrayList<Client>());
        cacheData();
        for (ClientEnt account: accounts) {
            temp.add(convertEntToClient(account));
        }
        return temp;
    }

    @Override
    public Client addClient(Client a) {
        try {
            return convertEntToClient(accountRepo.add(convertClientToEnt(a)));
        } catch (RepositoryException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void removeClient(Client a) {
        accountRepo.remove(convertClientToEnt(a));
    }

    @Override
    public Client getClient(Client a) {
        cacheData();
        if (accounts.contains(convertClientToEnt(a))) {
            return a;
        } else {
            return null;
        }
    }

    @Override
    public Client getMovieSelectedViaUUID(MovieRental movieRental) {
        return movieRental.getAccount();
    }

    @Override
    public Client getBookSelectedViaUUID(BookRental bookRental) {
        return bookRental.getAccount();
    }

    @Override
    public Optional<Client> getClientViaUUID(String str) {
        cacheData();
        return accounts
                .stream()
                .map(ClientConverter::convertEntToClient)
                .filter(
                        account -> account.getId().equals(str)
                )
                .findFirst();
    }

    @Override
    public void updateSingleAcc(Client accToChange, Client accWithData) {
        accountRepo.update(convertClientToEnt(accToChange),convertClientToEnt(accWithData));
    }
}
