package main.java.pl.lodz.p.it.applicationports.usecase.book;

import pl.lodz.p.it.applicationports.usecase.book.*;

public interface BookUsecaseSuit
    extends
        AddBookUsecase,
        GetAllBooksUsecase,
        GetBookUsecase,
        GetBookViaUUIDUsecase,
        RemoveBookUsecase,
        UpdateSingleBookUsecase,
        SetBookRentedUsecase

{
}
