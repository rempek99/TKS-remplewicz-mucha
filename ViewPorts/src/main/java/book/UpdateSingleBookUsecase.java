package book;

import modelDTO.BookDTO;

public interface UpdateSingleBookUsecase {
    void updateSingleBook(BookDTO income, BookDTO outcome);
}
