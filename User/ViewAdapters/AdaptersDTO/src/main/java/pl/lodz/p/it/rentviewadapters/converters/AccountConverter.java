package pl.lodz.p.it.rentviewadapters.converters;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Account;
import pl.lodz.p.it.rentviewmodel.modelDTO.AccountDTO;

public class AccountConverter {

    private AccountConverter() {
    }

    public static AccountDTO convertAccountToDTO(Account account){
        String firstName = account.getFirstName();
        String lastName = account.getLastName();
        String roleOfUser = account.getRoleOfUser();
        boolean isActive = account.isActive();
        String id = account.getId();
        String login = account.getLogin();
        String password = account.getPassword();

        AccountDTO newAccount = new AccountDTO(firstName, lastName, roleOfUser, isActive, login, password);
        newAccount.setId(id);

        return newAccount;
    }

    public static Account convertDTOToAccount(AccountDTO account){
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