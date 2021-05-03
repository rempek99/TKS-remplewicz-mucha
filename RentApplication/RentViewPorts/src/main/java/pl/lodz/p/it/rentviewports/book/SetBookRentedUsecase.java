package pl.lodz.p.it.rentviewports.book;

import pl.lodz.p.it.rentviewmodel.modelDTO.BookDTO;

public interface SetBookRentedUsecase<B> {
    void setBookRented(B b, boolean value);
}
