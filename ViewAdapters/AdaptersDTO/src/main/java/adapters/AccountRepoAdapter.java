package adapters;

import account.AccountUsecaseSuit;
import converters.AccountConverter;
import modelDTO.AccountDTO;
import modelDTO.BookRentalDTO;
import modelDTO.MovieRentalDTO;
import services.AccountService;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Dependent
public class AccountRepoAdapter implements AccountUsecaseSuit, Serializable{

    private AccountService accountService;
    private AccountConverter accountConverter;


    @Inject
    public AccountRepoAdapter(AccountService accountService) {
        this.accountService = accountService;
        accountConverter = new AccountConverter();
    }


    @Override
    public List<AccountDTO> getAllAccounts(){
        List<AccountDTO> accountDTOS = new ArrayList<>();
        accountService.getAll().forEach(r -> accountDTOS.add(getAccountViaUUID(r.getId())));
        return accountDTOS;
    }

    @Override
    public void addAccount(AccountDTO a) {
        accountService.add(accountConverter.convertDTOToAccount(a));
    }

    @Override
    public void removeAccount(AccountDTO a) {
        accountService.remove(accountConverter.convertDTOToAccount(a));
    }

    @Override
    public AccountDTO getAccount(AccountDTO a) {
        return accountConverter.convertAccountToDTO(accountService.get(accountConverter.convertDTOToAccount(a)));
    }

    @Override
    public AccountDTO getSingleMovieSelection(MovieRentalDTO movieRental) {
        return movieRental.getAccountDTO();
    }

    @Override
    public AccountDTO getSingleBookSelection(BookRentalDTO bookRental) {
        return bookRental.getAccountDTO();
    }

    @Override
    public AccountDTO getAccountViaUUID(String str) {
        return accountConverter.convertAccountToDTO(accountService.getViaUUID(str));
    }

    @Override
    public void updateSingleAccount(AccountDTO accToChange, AccountDTO accWithData) {
        accountService.update(accountConverter.convertDTOToAccount(accToChange), accountConverter.convertDTOToAccount(accWithData));
        //        Account fromRepo = accountRepo;
//        fromRepo.setActive(accWithData.isActive());
//        fromRepo.setFirstName(accWithData.getFirstName());
//        fromRepo.setLastName(accWithData.getLastName());
//        fromRepo.setLogin(accWithData.getLogin());
//        fromRepo.setPassword(accWithData.getPassword());
//        fromRepo.setRoleOfUser(accWithData.getRoleOfUser());
    }
}
