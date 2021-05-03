package pl.lodz.p.it.rentapplicationports.usecase.book;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Book;

public interface UpdateSingleBookUsecase {
    void update(Book income, Book outcome);
}
