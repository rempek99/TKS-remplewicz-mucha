package adapters;

import account.AccountUsecaseSuit;
import converters.AccountConverter;
import model.Account;
import modelDTO.AccountDTO;
import modelDTO.BookRentalDTO;
import modelDTO.MovieRentalDTO;
import repositories.AccountRepo;
import repositoriesDTO.AccountRepoDTO;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static converters.AccountConverter.convertAccountToDTO;
import static converters.AccountConverter.convertDTOToAccount;

@Dependent
public class AccountRepoAdapter implements AccountUsecaseSuit, Serializable{

    private final AccountRepoDTO accountRepo;
    private List<Account> accounts;


    @Inject
    public AccountRepoAdapter(AccountRepoDTO accountRepo) {
        this.accountRepo = accountRepo;
        cacheData();
    }
    private void cacheData() {
        accounts = accountRepo.getAll()
                .stream()
                .map(AccountConverter::convertDTOToAccount)
                .collect(Collectors.toList());
    }



    @Override
    public List<AccountDTO> getAllAccounts(){
        List<AccountDTO> temp = Collections.synchronizedList(new ArrayList<AccountDTO>());
        cacheData();
        for (Account account: accounts) {
            temp.add(convertAccountToDTO(account));
        }
        return temp;
    }

    @Override
    public void addAccount(AccountDTO a) {
        accountRepo.add(convertDTOToAccount(a));
    }

    @Override
    public void removeAccount(AccountDTO a) {
        accountRepo.remove(convertDTOToAccount(a));
    }

    @Override
    public AccountDTO getAccount(AccountDTO a) {
        cacheData();
        if (accounts.contains(convertDTOToAccount(a))) {
            return a;
        } else {
            return null;
        }
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
        cacheData();
        for(Account acc: accounts) {
            if(acc.getId().equals(str)){
                return convertAccountToDTO(acc);
            }
        }
        return null;
    }

    @Override
    public void updateSingleAccount(AccountDTO accToChange, AccountDTO accWithData) {
        accountRepo.update(convertDTOToAccount(accToChange),convertDTOToAccount(accWithData));
        //        Account fromRepo = accountRepo;
//        fromRepo.setActive(accWithData.isActive());
//        fromRepo.setFirstName(accWithData.getFirstName());
//        fromRepo.setLastName(accWithData.getLastName());
//        fromRepo.setLogin(accWithData.getLogin());
//        fromRepo.setPassword(accWithData.getPassword());
//        fromRepo.setRoleOfUser(accWithData.getRoleOfUser());
    }
}
