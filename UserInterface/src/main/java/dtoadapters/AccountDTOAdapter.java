package dtoadapters;

import controller.AccountService;
import dtoconverters.AccountDTOConverter;
import dtomodel.*;
import model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static dtoconverters.AccountDTOConverter.convertAccountToDTO;
import static dtoconverters.AccountDTOConverter.convertDTOToAccount;

public class AccountDTOAdapter {
    final AccountService accountService;
    final AccountDTOConverter accountDTOConverter;

    public AccountDTOAdapter(AccountService accountService, AccountDTOConverter accountDTOConverter) {
        this.accountService = accountService;
        this.accountDTOConverter = accountDTOConverter;
    }

    public List<AccountDTO> getAllAccounts(){
        List<AccountDTO> temp = Collections.synchronizedList(new ArrayList<AccountDTO>());
        List<Account> temp2 = accountService.getAllAccounts();
        for (Account account: temp2) {
            temp.add(convertAccountToDTO(account));
        }
        return temp;
    }

    public void addAccount(AccountDTO a) {
        accountService.addAccount(convertDTOToAccount(a));
    }

    public void removeAccount(AccountDTO a) {
        accountService.addAccount(convertDTOToAccount(a));
    }

    public AccountDTO getAccount(AccountDTO a) {
        List<AccountDTO> temp = getAllAccounts();
        if (temp.contains(a)) {
            return a;
        } else {
            return null;
        }
    }

    public AccountDTO getMovieSelectedViaUUID(Movie movie) {
        List<AccountDTO> temp = getAllAccounts();
        String compare = movie.getRentalUserUUID();
        for(AccountDTO acc: temp) {
            if(acc.getId().equals(compare)){
                return acc;
            }
        }
        return null;
    }

    public AccountDTO getBookSelectedViaUUID(Book book) {
        List<AccountDTO> temp = getAllAccounts();
        String compare = book.getRentalUserUUID();
        for(AccountDTO acc: temp) {
            if(acc.getId().equals(compare)){
                return acc;
            }
        }
        return null;
    }

    public AccountDTO getAccountViaUUID(String str) {
        List<AccountDTO> temp = getAllAccounts();
        for(AccountDTO acc: temp) {
            if(acc.getId().equals(str)){
                return acc;
            }
        }
        return null;
    }

    public void updateSingleAcc(AccountDTO accToChange, AccountDTO accWithData) {
        AccountDTO fromRepo = getAccount(accToChange);
        fromRepo.setActive(accWithData.isActive());
        fromRepo.setFirstName(accWithData.getFirstName());
        fromRepo.setLastName(accWithData.getLastName());
        fromRepo.setLogin(accWithData.getLogin());
        fromRepo.setPassword(accWithData.getPassword());
        fromRepo.setRoleOfUser(accWithData.getRoleOfUser());
    }
}
