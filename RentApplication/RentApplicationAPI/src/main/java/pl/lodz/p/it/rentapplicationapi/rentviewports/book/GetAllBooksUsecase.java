package pl.lodz.p.it.rentapplicationapi.rentviewports.book;

import java.util.List;

public interface GetAllBooksUsecase<B> {
    List<B> getAllBooks();
}
