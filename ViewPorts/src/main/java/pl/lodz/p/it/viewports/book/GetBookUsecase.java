package pl.lodz.p.it.viewports.book;

import pl.lodz.p.it.viewmodel.modelDTO.BookDTO;

public interface GetBookUsecase {
    BookDTO getBook(BookDTO b);
}
