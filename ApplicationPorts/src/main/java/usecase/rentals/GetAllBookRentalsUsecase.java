package usecase.rentals;

import model.BookRental;
import java.util.List;

public interface GetAllBookRentalsUsecase {
    List<BookRental> getAllBookRentals();
}
