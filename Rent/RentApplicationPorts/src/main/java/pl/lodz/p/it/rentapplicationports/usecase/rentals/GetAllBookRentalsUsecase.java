package pl.lodz.p.it.rentapplicationports.usecase.rentals;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.BookRental;
import java.util.List;

public interface GetAllBookRentalsUsecase {
    List<BookRental> getAllBookRentals();
}
