package usecase.book;

import model.Book;

public interface GetBookViaUUIDUsecase {
    Book getViaUUID(String str);
}
