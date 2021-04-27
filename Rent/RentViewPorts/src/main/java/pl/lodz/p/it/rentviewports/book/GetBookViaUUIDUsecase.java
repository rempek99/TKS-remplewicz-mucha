package pl.lodz.p.it.rentviewports.book;

import pl.lodz.p.it.rentviewmodel.modelDTO.BookDTO;

public interface GetBookViaUUIDUsecase<B> {
    B getBookViaUUID(String str);
}
