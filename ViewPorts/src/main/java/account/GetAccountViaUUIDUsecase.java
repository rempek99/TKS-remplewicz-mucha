package account;

import modelDTO.AccountDTO;

public interface GetAccountViaUUIDUsecase {
    AccountDTO getAccountViaUUID(String str);
}
