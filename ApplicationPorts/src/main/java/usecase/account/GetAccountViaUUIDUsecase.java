package usecase.account;

import model.Account;

public interface GetAccountViaUUIDUsecase {
    Account getAccountViaUUID(String str);
}
