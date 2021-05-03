package pl.lodz.p.it.rentviewports.book;

import pl.lodz.p.it.rentviewmodel.modelDTO.BookDTO;

public interface UpdateSingleBookUsecase<B> {
    void updateSingleBook(B income, B outcome);
}
