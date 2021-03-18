package usecase.rentals;

import model.BookRental;

public interface GetBookRentalViaUUIDUsecase {
    BookRental getBookRentalViaUUID(String str);
}
