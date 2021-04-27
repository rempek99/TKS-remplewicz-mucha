package pl.lodz.p.it.applicationports.usecase.book;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Book;
import java.util.List;

public interface GetAllBooksUsecase {
    List<Book> getAll();
}
