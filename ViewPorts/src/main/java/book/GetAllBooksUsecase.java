package book;

import modelDTO.BookDTO;
import java.util.List;

public interface GetAllBooksUsecase {
    List<BookDTO> getAllBooks();
}
