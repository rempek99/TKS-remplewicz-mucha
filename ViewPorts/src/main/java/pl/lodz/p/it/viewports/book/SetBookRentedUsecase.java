package pl.lodz.p.it.viewports.book;

import pl.lodz.p.it.viewmodel.modelDTO.BookDTO;

public interface SetBookRentedUsecase<B> {
    void setBookRented(B b, boolean value);
}
