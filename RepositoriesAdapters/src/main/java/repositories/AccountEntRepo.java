package repositories;


import model.entities.AccountEnt;
import model.entities.MovieEnt;

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
public class AccountEntRepo implements IdentityStore {

    private final List<AccountEnt> accounts = Collections.synchronizedList(new ArrayList<AccountEnt>());

    @PostConstruct
    private void insertInitData() {
        addAccount(new AccountEnt("Jan", "Kowalski", "USER", true, "jan12", "kowalski"));
        addAccount(new AccountEnt("Tomasz", "Nowak", "USER", true, "tomasz", "nowak"));
        addAccount(new AccountEnt("Artur", "Wiśniewski","USER", true, "artur", "wiśniewski"));
        addAccount(new AccountEnt("Janusz", "Szybki", "MANAGER", true, "janusz", "szybki"));
        addAccount(new AccountEnt("Mariusz", "Władczy", "ADMIN", true, "admin", "admin"));
    }

    public List<AccountEnt> getAllAccounts() {
            return Collections.unmodifiableList(accounts);
    }

    public AccountEnt addAccount(AccountEnt a) {
        a.setId(UUID.randomUUID().toString());
        accounts.add(a);
        return a;
    }

    public void removeAccount(AccountEnt a) {
            accounts.remove(a);
    }

    public AccountEnt getAccount(AccountEnt a) {
        if (accounts.contains(a)) {
            return a;
        } else {
           return null;
        }
    }

    public AccountEnt getMovieSelectedViaUUID(MovieEnt movie) {
        String compare = movie.getRentalUserUUID();
        for(AccountEnt acc: accounts) {
            if(acc.getId().equals(compare)){
                return acc;
            }
        }
        return null;
    }


//    todo implement this
//    public AccountEnt getBookSelectedViaUUID(BookEnt book) {
//        String compare = book.getRentalUserUUID();
//        for(Account acc: accounts) {
//            if(acc.getId().equals(compare)){
//                return acc;
//            }
//        }
//        return null;
//    }

    public AccountEnt getAccountViaUUID(String str) {
        for(AccountEnt acc: accounts) {
            if(acc.getId().equals(str)){
                return acc;
            }
        }
        return null;
    }

    public AccountEnt updateSingleAcc(AccountEnt accToChange, AccountEnt accWithData) {
        AccountEnt fromRepo = getAccount(accToChange);
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

        for (AccountEnt acc: accounts) {
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
        for(AccountEnt acc: accounts){
            if(acc.getLogin().equals(checked)){
                ((UIInput) comp).setValid(false);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"User already exists, choose another login", "User already exists, choose another login");
                context.addMessage(comp.getClientId(context), message);
            }
        }
    }

}
