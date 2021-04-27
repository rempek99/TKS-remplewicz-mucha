package main.java.pl.lodz.p.it.applicationports.usecase.book;

import pl.lodz.p.it.applicationcore.domainmodel.model.Book;
import java.util.List;

public interface GetAllBooksUsecase {
    List<Book> getAll();
}
