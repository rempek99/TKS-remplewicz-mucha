package rentals;

import modelDTO.BookRentalDTO;
import java.util.List;

public interface GetAllBookRentalsUsecase {
    List<BookRentalDTO> getAllBookRentals();
}
