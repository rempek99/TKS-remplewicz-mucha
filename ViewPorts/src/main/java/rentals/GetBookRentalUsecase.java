package rentals;

import modelDTO.BookRentalDTO;

public interface GetBookRentalUsecase {
    BookRentalDTO getBookRental(BookRentalDTO b);
}
