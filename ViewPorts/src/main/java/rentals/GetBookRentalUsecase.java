package rentals;

import modelDTO.BookRentalDTO;

public interface GetBookRentalUsecase {
    BookRentalDTO getBookRental(String str);
}
