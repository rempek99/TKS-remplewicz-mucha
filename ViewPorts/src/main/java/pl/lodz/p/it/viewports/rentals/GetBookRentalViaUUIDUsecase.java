package pl.lodz.p.it.viewports.rentals;

import pl.lodz.p.it.viewmodel.modelDTO.BookRentalDTO;

public interface GetBookRentalViaUUIDUsecase {
    BookRentalDTO getBookRentalViaUUID(String str);
}
