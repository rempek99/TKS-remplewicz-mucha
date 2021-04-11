package pl.lodz.p.it.viewadapters.adapters;

import pl.lodz.p.it.applicationports.usecase.account.AccountUsecaseSuit;
import pl.lodz.p.it.viewadapters.converters.AccountConverter;
import pl.lodz.p.it.viewmodel.modelDTO.AccountDTO;
import pl.lodz.p.it.viewmodel.modelDTO.BookRentalDTO;
import pl.lodz.p.it.viewmodel.modelDTO.MovieRentalDTO;
import pl.lodz.p.it.viewports.account.AccountViewPortUsecaseSuit;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Dependent
public class AccountServiceAdapter implements AccountViewPortUsecaseSuit, Serializable{

    AccountUsecaseSuit accountService;

    @Inject
    public AccountServiceAdapter(AccountUsecaseSuit accountService) {
        this.accountService = accountService;
    }


    @Override
    public List<AccountDTO> getAllAccounts(){
        List<AccountDTO> accountDTOS = new ArrayList<>();
        accountService.getAll().forEach(r -> accountDTOS.add(getAccountViaUUID(r.getId())));
        return accountDTOS;
    }

    @Override
    public void addAccount(AccountDTO a) {
        accountService.add(AccountConverter.convertDTOToAccount(a));
    }

    @Override
    public void removeAccount(AccountDTO a) {
        accountService.remove(AccountConverter.convertDTOToAccount(a));
    }

    @Override
    public AccountDTO getAccount(AccountDTO a) {
        return AccountConverter.convertAccountToDTO(
                accountService.get(AccountConverter.convertDTOToAccount(a))
        );
    }

    @Override
    public AccountDTO getSingleMovieSelection(MovieRentalDTO movieRental) {
        return movieRental.getAccount();
    }

    @Override
    public AccountDTO getSingleBookSelection(BookRentalDTO bookRental) {
        return bookRental.getAccount();
    }

    @Override
    public AccountDTO getAccountViaUUID(String str) {
        return AccountConverter.convertAccountToDTO(accountService.getViaUUID(str));
    }

    @Override
    public void updateSingleAccount(AccountDTO accToChange, AccountDTO accWithData) {
        accountService.update(AccountConverter.convertDTOToAccount(accToChange), AccountConverter.convertDTOToAccount(accWithData));
    }

    @Override
    public void setAccountStatus(String id, boolean status, String role) {
        accountService.setAccountStatus(id, status, role);
    }
}
