package pl.lodz.p.it.rentrepositoriesadapters.model_ent.repositories;

import pl.lodz.p.it.rentrepositoriesadapters.model_ent.entities.ClientEnt;

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
import java.util.*;

@ApplicationScoped
public class ClientEntRepo implements IdentityStore, IRepositoryEnt<ClientEnt> {

    private final List<ClientEnt> accounts = Collections.synchronizedList(new ArrayList<ClientEnt>());

    @PostConstruct
    private void insertInitData() {
        try {
            add(new ClientEnt("Jan", "Kowalski", "USER", true, "jan12", "kowalski"));
            add(new ClientEnt("Tomasz", "Nowak", "USER", true, "tomasz", "nowak"));
            add(new ClientEnt("Artur", "Wiśniewski", "USER", true, "artur", "wiśniewski"));
            add(new ClientEnt("Janusz", "Szybki", "MANAGER", true, "janusz", "szybki"));
            add(new ClientEnt("Mariusz", "Władczy", "ADMIN", true, "admin", "admin"));
        } catch (RepositoryException e) {
            e.printStackTrace();
        }

    }

    public List<ClientEnt> getAll() {
        return Collections.unmodifiableList(accounts);
    }

    public ClientEnt add(ClientEnt a) throws RepositoryException {
        if(accounts
                .stream()
                .anyMatch(account -> account.getLogin().equals(a.getLogin())))
            throw new RepositoryException(RepositoryException.DUPLICATED);
        a.setId(UUID.randomUUID().toString());
        accounts.add(a);
        return a;
    }

    public void remove(ClientEnt a) {
        accounts.remove(a);
    }

    public ClientEnt get(ClientEnt a) {
        Optional<ClientEnt> accountEnt = accounts.stream()
                .filter(x -> x.equals(a))
                .findFirst();
        return accountEnt.orElse(null);
    }

    public ClientEnt getViaUUID(String str) {
        for (ClientEnt acc : accounts) {
            if (acc.getId().equals(str)) {
                return acc;
            }
        }
        return null;
    }

    public ClientEnt update(ClientEnt accToChange, ClientEnt accWithData) {
        ClientEnt fromRepo = get(accToChange);
        fromRepo.setActive(accWithData.isActive());
        fromRepo.setFirstName(accWithData.getFirstName());
        fromRepo.setLastName(accWithData.getLastName());
        fromRepo.setLogin(accWithData.getLogin());
        fromRepo.setPassword(accWithData.getPassword());
        fromRepo.setRoleOfUser(accWithData.getRoleOfUser());
        return fromRepo;
    }

    @Override
    public CredentialValidationResult validate(Credential credential) {

        UsernamePasswordCredential login = (UsernamePasswordCredential) credential;
        CredentialValidationResult res = null;

        for (ClientEnt acc : accounts) {
            if (login.getCaller().equals(acc.getLogin()) && login.getPasswordAsString().equals(acc.getPassword())) {
                res = new CredentialValidationResult(acc.getId(), new HashSet<>(Collections.singletonList(acc.getRoleOfUser())));
                break;
            } else {
                res = CredentialValidationResult.NOT_VALIDATED_RESULT;
            }
        }
        return res;
    }

    public void checkIfUsernameExists(FacesContext context, UIComponent comp, Object value) throws ValidatorException {
        String checked = (String) value;
        for (ClientEnt acc : accounts) {
            if (acc.getLogin().equals(checked)) {
                ((UIInput) comp).setValid(false);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "User already exists, choose another login", "User already exists, choose another login");
                context.addMessage(comp.getClientId(context), message);
            }
        }
    }

}
