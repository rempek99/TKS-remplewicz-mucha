package main.java.pl.lodz.p.it.applicationports.usecase.rentals;

import pl.lodz.p.it.applicationcore.domainmodel.model.BookRental;

public interface GetBookRentalUsecase {
    BookRental getBookRental(String str);
}
