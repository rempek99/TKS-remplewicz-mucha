package pl.lodz.p.it.applicationports.usecase.book;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Book;

public interface GetBookUsecase {
    Book get(Book b);
}
