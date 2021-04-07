package book;

import modelDTO.BookDTO;

public interface SetBookRentedUsecase {
    void setBookRented(BookDTO b, boolean value);
}
