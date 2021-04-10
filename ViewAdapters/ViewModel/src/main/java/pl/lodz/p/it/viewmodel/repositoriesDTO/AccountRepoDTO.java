package pl.lodz.p.it.viewmodel.repositoriesDTO;

import pl.lodz.p.it.viewmodel.modelDTO.AccountDTO;

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
public class AccountRepoDTO implements IdentityStore, IRepository<AccountDTO> {

    private List<AccountDTO> accounts = Collections.synchronizedList(new ArrayList<AccountDTO>());

    @PostConstruct
    private void insertInitData() {
        accounts.add(new AccountDTO("Jan", "Kowalski", "USER", true, "jan12", "kowalski"));
        accounts.add(new AccountDTO("Tomasz", "Nowak", "USER", true, "tomasz", "nowak"));
        accounts.add(new AccountDTO("Artur", "Wiśniewski","USER", true, "artur", "wiśniewski"));
        accounts.add(new AccountDTO("Janusz", "Szybki", "MANAGER", true, "janusz", "szybki"));
        accounts.add(new AccountDTO("Mariusz", "Władczy", "ADMIN", true, "admin", "admin"));
    }

    public List<AccountDTO> getAll() {
            return Collections.unmodifiableList(accounts);
    }

    public AccountDTO add(AccountDTO a) {
            accounts.add(a);
            a.setId(UUID.randomUUID().toString());
//            printState();
            return a;
    }

    public void remove(AccountDTO a) {
            accounts.remove(a);
//            printState();
    }

    public AccountDTO get(AccountDTO a) {
        if (accounts.contains(a)) {
            return a;
        } else {
           return null;
        }
    }

    public AccountDTO getViaUUID(String str) {
        for(AccountDTO acc: accounts) {
            if(acc.getId().equals(str)){
                return acc;
            }
        }
        return null;
    }

    public AccountDTO update(AccountDTO accToChange, AccountDTO accWithData) {
        AccountDTO fromRepo = get(accToChange);
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

        for (AccountDTO acc: accounts) {
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
        for(AccountDTO acc: accounts){
            if(acc.getLogin().equals(checked)){
                ((UIInput) comp).setValid(false);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"User already exists, choose another login", "User already exists, choose another login");
                context.addMessage(comp.getClientId(context), message);
            }
        }
    }

}
