package pl.lodz.p.it.viewports.rentals;

import pl.lodz.p.it.viewmodel.modelDTO.BookRentalDTO;

public interface UpdateSingleBookRentalUsecase {
    void updateSingleBookRental(BookRentalDTO income, BookRentalDTO outcome);
}
