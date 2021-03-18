package usecase.book;

import model.Book;

public interface GetBookViaUUIDUsecase {
    Book getBookViaUUID(String str);
}
