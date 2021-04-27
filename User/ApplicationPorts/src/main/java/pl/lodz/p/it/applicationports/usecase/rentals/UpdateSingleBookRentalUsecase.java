package pl.lodz.p.it.applicationports.usecase.rentals;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.BookRental;

public interface UpdateSingleBookRentalUsecase {
    void updateSingleBookRental(BookRental income, BookRental outcome);
}
