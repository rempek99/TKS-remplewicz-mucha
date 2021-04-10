package pl.lodz.p.it.applicationports.usecase.rentals;

import pl.lodz.p.it.applicationcore.domainmodel.model.BookRental;
import java.util.List;

public interface GetAllBookRentalsUsecase {
    List<BookRental> getAllBookRentals();
}
