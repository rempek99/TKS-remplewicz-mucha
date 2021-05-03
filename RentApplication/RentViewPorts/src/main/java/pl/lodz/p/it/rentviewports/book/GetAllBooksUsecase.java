package pl.lodz.p.it.rentviewports.book;

import pl.lodz.p.it.rentviewmodel.modelDTO.BookDTO;
import java.util.List;

public interface GetAllBooksUsecase<B> {
    List<B> getAllBooks();
}
