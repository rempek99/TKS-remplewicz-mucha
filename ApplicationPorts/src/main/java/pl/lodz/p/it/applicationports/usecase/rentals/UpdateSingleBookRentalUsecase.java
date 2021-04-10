package pl.lodz.p.it.applicationports.usecase.rentals;

import pl.lodz.p.it.applicationcore.domainmodel.model.BookRental;

public interface UpdateSingleBookRentalUsecase {
    void updateSingleBookRental(BookRental income, BookRental outcome);
}
