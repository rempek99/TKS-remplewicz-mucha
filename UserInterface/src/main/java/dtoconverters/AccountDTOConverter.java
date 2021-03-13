package dtoconverters;

import dtomodel.AccountDTO;
import model.Account;

public class AccountDTOConverter {
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
