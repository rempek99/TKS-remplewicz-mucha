package pl.lodz.p.it.viewports.rentals;

import pl.lodz.p.it.viewmodel.modelDTO.BookRentalDTO;

public interface GetBookRentalUsecase {
    BookRentalDTO getBookRental(BookRentalDTO b);
}
