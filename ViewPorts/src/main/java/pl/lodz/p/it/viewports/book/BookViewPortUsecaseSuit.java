package pl.lodz.p.it.viewports.book;

public interface BookViewPortUsecaseSuit
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
