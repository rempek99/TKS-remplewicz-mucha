package pl.lodz.p.it.soap.aggregates.adapters;


import pl.lodz.p.it.applicationports.usecase.account.AccountUsecaseSuit;
import pl.lodz.p.it.soap.aggregates.converters.AccountSoapConverter;
import pl.lodz.p.it.soap.model.AccountSoap;
import pl.lodz.p.it.soap.model.BookRentalSoap;
import pl.lodz.p.it.soap.model.MovieRentalSoap;
import pl.lodz.p.it.viewports.account.AccountViewPortUsecaseSuit;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Dependent
public class AccountSoapAdapter implements AccountViewPortUsecaseSuit<AccountSoap, BookRentalSoap, MovieRentalSoap>, Serializable {

    AccountUsecaseSuit accountService;

    @Inject
    public AccountSoapAdapter(AccountUsecaseSuit accountService) {
        this.accountService = accountService;
    }

    @Override
    public void addAccount(AccountSoap a) {
        accountService.add(AccountSoapConverter.convertAccountSoapToAccount(a));
    }

    @Override
    public AccountSoap getAccount(AccountSoap a) {
        return AccountSoapConverter.convertAccountToAccountSoap(
                accountService.get(AccountSoapConverter.convertAccountSoapToAccount(a))
        );
    }

    @Override
    public AccountSoap getAccountViaUUID(String str) {
        return AccountSoapConverter.convertAccountToAccountSoap(
                accountService.getViaUUID(str));
    }

    @Override
    public List<AccountSoap> getAllAccounts() {
        return accountService
                .getAll()
                .stream()
                .map(AccountSoapConverter::convertAccountToAccountSoap)
                .collect(Collectors.toList());
    }

    @Override
    public AccountSoap getSingleBookSelection(BookRentalSoap b) {
        return b.getAccount();
    }

    @Override
    public AccountSoap getSingleMovieSelection(MovieRentalSoap m) {
        return m.getAccount();
    }

    @Override
    public void removeAccount(AccountSoap a) {
        accountService.remove(AccountSoapConverter.convertAccountSoapToAccount(a));
    }

    @Override
    public void setAccountStatus(String id, boolean status, String role) {
        accountService.setAccountStatus(id, status, role);
    }

    @Override
    public void updateSingleAccount(AccountSoap income, AccountSoap outcome) {
        accountService.update(
                AccountSoapConverter.convertAccountSoapToAccount(income),
                AccountSoapConverter.convertAccountSoapToAccount(outcome));
    }
}
