package pl.lodz.p.it.viewports.book;

import pl.lodz.p.it.viewmodel.modelDTO.BookDTO;
import java.util.List;

public interface GetAllBooksUsecase {
    List<BookDTO> getAllBooks();
}
