package pl.lodz.p.it.rentapplicationcore.rentapplicationservice.services;

import pl.lodz.p.it.rentapplicationports.infrastructure.ClientPort;
import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Client;
import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.BookRental;
import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.MovieRental;
import pl.lodz.p.it.rentapplicationports.usecase.client.ClientUsecaseSuit;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Dependent
public class ClientService implements Serializable, ClientUsecaseSuit, IService<Client> {


    private ClientPort accountRepo;

    @Inject
    public ClientService(ClientPort accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public List<Client> getAll() {
        return accountRepo.getAllClients();
    }

    @Override
    public Client getSingleMovieSelection(MovieRental m) {
        return accountRepo.getMovieSelectedViaUUID(m);
    }

    @Override
    public Client getSingleBookSelection(BookRental b) {return accountRepo.getBookSelectedViaUUID(b); }

    @Override
    public Optional<Client> getViaUUID(String str) {
        return accountRepo.getClientViaUUID(str);
    }

    @Override
    public void update(Client income, Client outcome) {accountRepo.updateSingleAcc(income, outcome);}

    @Override
    public Client add(Client a) throws IllegalArgumentException {
        return accountRepo.addClient(a);
    }

    @Override
    public void remove(Client a) {
        accountRepo.removeClient(a);
    }

    @Override
    public Client get(Client a) {
        return accountRepo.getClient(a);
    }

    @Override
    public void setClientStatus(String id, boolean status, String role) {
        Optional<Client> accountOpt = accountRepo.getClientViaUUID(id);
        if (accountOpt.isPresent()) {
            Client account = accountOpt.get();
            Client newClient = new Client(account.getFirstName(), account.getLastName(), role, status, account.getLogin(), account.getPassword());
            accountRepo.updateSingleAcc(account, newClient);
        }
        else {
            throw new IllegalArgumentException("Not found");
        }
    }
}
