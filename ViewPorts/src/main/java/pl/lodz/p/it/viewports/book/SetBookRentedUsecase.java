package pl.lodz.p.it.viewports.book;

import pl.lodz.p.it.viewmodel.modelDTO.BookDTO;

public interface SetBookRentedUsecase {
    void setBookRented(BookDTO b, boolean value);
}
