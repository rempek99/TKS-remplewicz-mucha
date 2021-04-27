package pl.lodz.p.it.rentviewports.rentals;

import pl.lodz.p.it.rentviewmodel.modelDTO.BookRentalDTO;
import java.util.List;

public interface GetAllBookRentalsUsecase<BR> {
    List<BR> getAllBookRentals();
}
