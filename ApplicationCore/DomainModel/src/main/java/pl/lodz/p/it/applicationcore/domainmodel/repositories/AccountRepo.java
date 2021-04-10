package pl.lodz.p.it.applicationcore.domainmodel.repositories;

import pl.lodz.p.it.applicationcore.domainmodel.model.Account;

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
public class AccountRepo implements IdentityStore, IRepository<Account> {

    private List<Account> accounts = Collections.synchronizedList(new ArrayList<Account>());

    @PostConstruct
    private void insertInitData() {
        accounts.add(new Account("Jan", "Kowalski", "USER", true, "jan12", "kowalski"));
        accounts.add(new Account("Tomasz", "Nowak", "USER", true, "tomasz", "nowak"));
        accounts.add(new Account("Artur", "Wiśniewski","USER", true, "artur", "wiśniewski"));
        accounts.add(new Account("Janusz", "Szybki", "MANAGER", true, "janusz", "szybki"));
        accounts.add(new Account("Mariusz", "Władczy", "ADMIN", true, "admin", "admin"));
    }

    public List<Account> getAll() {
            return Collections.unmodifiableList(accounts);
    }

    public Account add(Account a) {
            accounts.add(a);
            a.setId(UUID.randomUUID().toString());
//            printState();
            return a;
    }

    public void remove(Account a) {
            accounts.remove(a);
//            printState();
    }

    public Account get(Account a) {
        if (accounts.contains(a)) {
            return a;
        } else {
           return null;
        }
    }

    public Account getViaUUID(String str) {
        for(Account acc: accounts) {
            if(acc.getId().equals(str)){
                return acc;
            }
        }
        return null;
    }

    public Account update(Account accToChange, Account accWithData) {
        Account fromRepo = get(accToChange);
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

        for (Account acc: accounts) {
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
        for(Account acc: accounts){
            if(acc.getLogin().equals(checked)){
                ((UIInput) comp).setValid(false);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"User already exists, choose another login", "User already exists, choose another login");
                context.addMessage(comp.getClientId(context), message);
            }
        }
    }

}
