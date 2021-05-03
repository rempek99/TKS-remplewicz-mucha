package pl.lodz.p.it.rentapplicationports.usecase.book;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Book;

import java.util.Optional;

public interface GetBookViaUUIDUsecase {
    Optional<Book> getViaUUID(String str);
}
