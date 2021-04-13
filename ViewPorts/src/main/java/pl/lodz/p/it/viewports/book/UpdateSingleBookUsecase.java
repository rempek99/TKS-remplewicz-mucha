package pl.lodz.p.it.viewports.book;

import pl.lodz.p.it.viewmodel.modelDTO.BookDTO;

public interface UpdateSingleBookUsecase<B> {
    void updateSingleBook(B income, B outcome);
}
