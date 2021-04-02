package rentals;

import modelDTO.BookRentalDTO;

public interface UpdateSingleBookRentalUsecase {
    void updateSingleBookRental(BookRentalDTO income, BookRentalDTO outcome);
}
