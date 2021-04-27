package pl.lodz.p.it.applicationports.usecase.rentals;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.BookRental;

import java.util.Optional;

public interface GetBookRentalViaUUIDUsecase {
    Optional<BookRental> getBookRentalViaUUID(String str);
}
