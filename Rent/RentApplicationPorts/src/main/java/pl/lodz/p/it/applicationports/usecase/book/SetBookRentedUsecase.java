package main.java.pl.lodz.p.it.applicationports.usecase.book;

import pl.lodz.p.it.applicationcore.domainmodel.model.Book;

public interface SetBookRentedUsecase {
    void setBookRented(Book b, boolean value);
}
