package main.java.pl.lodz.p.it.applicationports.usecase.rentals;

import pl.lodz.p.it.applicationcore.domainmodel.model.BookRental;

public interface RemoveBookRentalUsecase {
    void removeBookRental(BookRental r);
}
