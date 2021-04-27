package pl.lodz.p.it.rentrepositoriesadapters.model_ent.repositories;

import pl.lodz.p.it.rentrepositoriesadapters.model_ent.entities.AccountEnt;
import pl.lodz.p.it.rentrepositoriesadapters.model_ent.repositories.IRepositoryEnt;
import pl.lodz.p.it.rentrepositoriesadapters.model_ent.repositories.RepositoryException;

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
public class AccountEntRepo implements IdentityStore, IRepositoryEnt<AccountEnt> {

    private final List<AccountEnt> accounts = Collections.synchronizedList(new ArrayList<AccountEnt>());

    @PostConstruct
    private void insertInitData() {
        try {
            add(new AccountEnt("Jan", "Kowalski", "USER", true, "jan12", "kowalski"));
            add(new AccountEnt("Tomasz", "Nowak", "USER", true, "tomasz", "nowak"));
            add(new AccountEnt("Artur", "Wiśniewski", "USER", true, "artur", "wiśniewski"));
            add(new AccountEnt("Janusz", "Szybki", "MANAGER", true, "janusz", "szybki"));
            add(new AccountEnt("Mariusz", "Władczy", "ADMIN", true, "admin", "admin"));
        } catch (RepositoryException e) {
            e.printStackTrace();
        }

    }

    public List<AccountEnt> getAll() {
        return Collections.unmodifiableList(accounts);
    }

    public AccountEnt add(AccountEnt a) throws RepositoryException {
        if(accounts
                .stream()
                .anyMatch(account -> account.getLogin().equals(a.getLogin())))
            throw new RepositoryException(RepositoryException.DUPLICATED);
        a.setId(UUID.randomUUID().toString());
        accounts.add(a);
        return a;
    }

    public void remove(AccountEnt a) {
        accounts.remove(a);
    }

    public AccountEnt get(AccountEnt a) {
        Optional<AccountEnt> accountEnt = accounts.stream()
                .filter(x -> x.equals(a))
                .findFirst();
        return accountEnt.orElse(null);
    }

    public AccountEnt getViaUUID(String str) {
        for (AccountEnt acc : accounts) {
            if (acc.getId().equals(str)) {
                return acc;
            }
        }
        return null;
    }

    public AccountEnt update(AccountEnt accToChange, AccountEnt accWithData) {
        AccountEnt fromRepo = get(accToChange);
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

        for (AccountEnt acc : accounts) {
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
        for (AccountEnt acc : accounts) {
            if (acc.getLogin().equals(checked)) {
                ((UIInput) comp).setValid(false);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "User already exists, choose another login", "User already exists, choose another login");
                context.addMessage(comp.getClientId(context), message);
            }
        }
    }

}
