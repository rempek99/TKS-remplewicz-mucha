package pl.lodz.p.it.viewports.rentals;

import pl.lodz.p.it.viewmodel.modelDTO.BookRentalDTO;
import java.util.List;

public interface GetAllBookRentalsUsecase<BR> {
    List<BR> getAllBookRentals();
}
