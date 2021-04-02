package book;

import modelDTO.BookDTO;

public interface GetBookViaUUIDUsecase {
    BookDTO getBookViaUUID(String str);
}
