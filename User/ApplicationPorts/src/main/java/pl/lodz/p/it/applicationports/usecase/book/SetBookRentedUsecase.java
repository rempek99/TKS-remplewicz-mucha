package pl.lodz.p.it.applicationports.usecase.book;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Book;

public interface SetBookRentedUsecase {
    void setBookRented(Book b, boolean value);
}
