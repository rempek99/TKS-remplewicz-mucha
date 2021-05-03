package pl.lodz.p.it.rentapplicationcore.rentdomainmodel.repositories;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Client;
import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.repositories.IRepository;

import java.util.*;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

@ApplicationScoped
public class ClientRepo implements IdentityStore, IRepository<Client> {

    private List<Client> accounts = Collections.synchronizedList(new ArrayList<Client>());

    @PostConstruct
    private void insertInitData() {
        accounts.add(new Client("Jan", "Kowalski", "USER", true, "jan12", "kowalski"));
        accounts.add(new Client("Tomasz", "Nowak", "USER", true, "tomasz", "nowak"));
        accounts.add(new Client("Artur", "Wiśniewski","USER", true, "artur", "wiśniewski"));
        accounts.add(new Client("Janusz", "Szybki", "MANAGER", true, "janusz", "szybki"));
        accounts.add(new Client("Mariusz", "Władczy", "ADMIN", true, "admin", "admin"));
    }

    public List<Client> getAll() {
            return Collections.unmodifiableList(accounts);
    }

    public Client add(Client a) {
            accounts.add(a);
            a.setId(UUID.randomUUID().toString());
//            printState();
            return a;
    }

    public void remove(Client a) {
            accounts.remove(a);
//            printState();
    }

    public Client get(Client a) {
        if (accounts.contains(a)) {
            return a;
        } else {
           return null;
        }
    }

    public Client getViaUUID(String str) {
        for(Client acc: accounts) {
            if(acc.getId().equals(str)){
                return acc;
            }
        }
        return null;
    }

    public Client update(Client accToChange, Client accWithData) {
        Client fromRepo = get(accToChange);
        fromRepo.setActive(accWithData.isActive());
        fromRepo.setFirstName(accWithData.getFirstName());
        fromRepo.setLastName(accWithData.getLastName());
        fromRepo.setLogin(accWithData.getLogin());
        fromRepo.setPassword(accWithData.getPassword());
        fromRepo.setRoleOfUser(accWithData.getRoleOfUser());
        return fromRepo;
    }

    private void printState() {
        System.out.println(Arrays.toString(accounts.toArray()));
    }

    @Override
    public CredentialValidationResult validate(Credential credential) {

        UsernamePasswordCredential login = (UsernamePasswordCredential) credential;
        CredentialValidationResult res = null;

        for (Client acc: accounts) {
            if (login.getCaller().equals(acc.getLogin()) && login.getPasswordAsString().equals(acc.getPassword())) {
                res = new CredentialValidationResult(acc.getId(), new HashSet<>(Collections.singletonList(acc.getRoleOfUser())));
                break;
            }
            else {
               res = CredentialValidationResult.NOT_VALIDATED_RESULT;
            }
        }
        return res;
    }

    public void checkIfUsernameExists(FacesContext context, UIComponent comp, Object value) throws ValidatorException {
        String checked = (String) value;
        for(Client acc: accounts){
            if(acc.getLogin().equals(checked)){
                ((UIInput) comp).setValid(false);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"User already exists, choose another login", "User already exists, choose another login");
                context.addMessage(comp.getClientId(context), message);
            }
        }
    }

}
