package usecase.account;

import model.Account;

public interface GetAccountViaUUIDUsecase {
    Account getViaUUID(String str);
}
