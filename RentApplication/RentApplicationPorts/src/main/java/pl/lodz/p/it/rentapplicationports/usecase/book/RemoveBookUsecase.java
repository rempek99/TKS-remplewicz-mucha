package pl.lodz.p.it.rentapplicationports.usecase.book;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Book;

public interface RemoveBookUsecase {
    void remove(Book b);
}