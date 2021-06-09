package pl.lodz.p.it.user.userapplicationapi.aggregates.converters;


import pl.lodz.p.it.topicmodels.dtos.UserDTO;
import pl.lodz.p.it.user.userapplicationapi.modelDTO.AccountDTO;
import pl.lodz.p.it.user.userapplicationcore.userdomainmodel.model.Account;

public class AccountConverter {

    private AccountConverter() {
    }
    public static AccountDTO convertFromUserKafkaDTO(UserDTO user){
        return new AccountDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getRoleOfUser(),
                user.isActive(),
                user.getLogin(),
                user.getPassword()
        );
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