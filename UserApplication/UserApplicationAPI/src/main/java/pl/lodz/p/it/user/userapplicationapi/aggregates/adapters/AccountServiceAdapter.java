package pl.lodz.p.it.user.userapplicationapi.aggregates.adapters;

import pl.lodz.p.it.user.userapplicationapi.aggregates.converters.AccountConverter;
import pl.lodz.p.it.user.userapplicationapi.exceptions.RestException;
import pl.lodz.p.it.user.userapplicationapi.modelDTO.AccountDTO;
import pl.lodz.p.it.user.userapplicationapi.userviewports.client.AccountViewPortUsecaseSuit;
import pl.lodz.p.it.user.userapplicationports.client.AccountUsecaseSuit;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Dependent
public class AccountServiceAdapter implements AccountViewPortUsecaseSuit<AccountDTO>, Serializable{

    AccountUsecaseSuit accountService;

    @Inject
    public AccountServiceAdapter(AccountUsecaseSuit accountService) {
        this.accountService = accountService;
    }


    @Override
    public List<AccountDTO> getAllAccounts(){
        return accountService
                .getAll()
                .stream()
                .map(AccountConverter::convertAccountToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDTO addAccount(AccountDTO a) {
        return AccountConverter.convertAccountToDTO(
                accountService.add(AccountConverter.convertDTOToAccount(a))
        );
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
    public AccountDTO getAccountViaUUID(String str) {
        if(accountService.getViaUUID(str).isPresent()) {
            return AccountConverter.convertAccountToDTO(accountService.getViaUUID(str).get());
        }
        else{
            throw new IllegalArgumentException(RestException.NOT_FOUND);
        }
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
