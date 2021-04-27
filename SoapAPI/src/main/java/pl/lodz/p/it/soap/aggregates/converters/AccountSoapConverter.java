package pl.lodz.p.it.soap.aggregates.converters;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Account;
import pl.lodz.p.it.soap.model.AccountSoap;

public class AccountSoapConverter {

    private AccountSoapConverter() {
    }

    public static AccountSoap convertAccountToAccountSoap(Account account){
        AccountSoap newAccount = new AccountSoap(
                account.getFirstName(),
                account.getLastName(),
                account.getRoleOfUser(),
                account.isActive(),
                account.getLogin(),
                account.getPassword());
        newAccount.setId(account.getId());
        return newAccount;
    }

    public static Account convertAccountSoapToAccount(AccountSoap account){
        Account newAccount = new Account(
                account.getFirstName(),
                account.getLastName(),
                account.getRoleOfUser(),
                account.isActive(),
                account.getLogin(),
                account.getPassword());
        newAccount.setId(account.getId());
        return newAccount;
    }
}
