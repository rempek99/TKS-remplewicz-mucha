package pl.lodz.p.it.rentapplicationapi.rentviewmodel.repositoriesDTO;


import pl.lodz.p.it.rentapplicationapi.rentviewmodel.modelDTO.ClientDTO;

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
public class ClientRepoDTO implements IdentityStore, IRepository<ClientDTO> {

    private List<ClientDTO> accounts = Collections.synchronizedList(new ArrayList<ClientDTO>());

    @PostConstruct
    private void insertInitData() {
        accounts.add(new ClientDTO("Jan", "Kowalski", "USER", true, "jan12", "kowalski"));
        accounts.add(new ClientDTO("Tomasz", "Nowak", "USER", true, "tomasz", "nowak"));
        accounts.add(new ClientDTO("Artur", "Wiśniewski","USER", true, "artur", "wiśniewski"));
        accounts.add(new ClientDTO("Janusz", "Szybki", "MANAGER", true, "janusz", "szybki"));
        accounts.add(new ClientDTO("Mariusz", "Władczy", "ADMIN", true, "admin", "admin"));
    }

    public List<ClientDTO> getAll() {
            return Collections.unmodifiableList(accounts);
    }

    public ClientDTO add(ClientDTO a) {
            accounts.add(a);
            a.setId(UUID.randomUUID().toString());
//            printState();
            return a;
    }

    public void remove(ClientDTO a) {
            accounts.remove(a);
//            printState();
    }

    public ClientDTO get(ClientDTO a) {
        if (accounts.contains(a)) {
            return a;
        } else {
           return null;
        }
    }

    public ClientDTO getViaUUID(String str) {
        for(ClientDTO acc: accounts) {
            if(acc.getId().equals(str)){
                return acc;
            }
        }
        return null;
    }

    public ClientDTO update(ClientDTO accToChange, ClientDTO accWithData) {
        ClientDTO fromRepo = get(accToChange);
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

        for (ClientDTO acc: accounts) {
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
        for(ClientDTO acc: accounts){
            if(acc.getLogin().equals(checked)){
                ((UIInput) comp).setValid(false);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"User already exists, choose another login", "User already exists, choose another login");
                context.addMessage(comp.getClientId(context), message);
            }
        }
    }

}
