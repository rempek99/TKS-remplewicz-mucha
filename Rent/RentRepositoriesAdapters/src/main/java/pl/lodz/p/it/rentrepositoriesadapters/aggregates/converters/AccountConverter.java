package main.java.pl.lodz.p.it.repositoriesadapters.aggregates.converters;

import pl.lodz.p.it.applicationcore.domainmodel.model.Account;
import pl.lodz.p.it.rentrepositoriesadapters.model_ent.entities.AccountEnt;

public class AccountConverter {

    private AccountConverter() {
    }

    public static AccountEnt convertAccountToEnt(Account account){
        String firstName = account.getFirstName();
        String lastName = account.getLastName();
        String roleOfUser = account.getRoleOfUser();
        boolean isActive = account.isActive();
        String id = account.getId();
        String login = account.getLogin();
        String password = account.getPassword();

        AccountEnt newAccount = new AccountEnt(firstName, lastName, roleOfUser, isActive, login, password);
        newAccount.setId(id);

        return newAccount;
    }

    public static Account convertEntToAccount(AccountEnt account){
        String firstName = account.getFirstName();
        String lastName = account.getLastName();
        String roleOfUser = account.getRoleOfUser();
        boolean isActive = account.isActive();
        String id = account.getId();
        String login = account.getLogin();
        String password = account.getPassword();

        Account newAccount = new Account(firstName, lastName, roleOfUser, isActive, login, password);
        newAccount.setId(id);

        return newAccount;
    }
}
