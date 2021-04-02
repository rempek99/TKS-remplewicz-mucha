package rentals;

import modelDTO.BookRentalDTO;

public interface GetBookRentalViaUUIDUsecase {
    BookRentalDTO getBookRentalViaUUID(String str);
}
