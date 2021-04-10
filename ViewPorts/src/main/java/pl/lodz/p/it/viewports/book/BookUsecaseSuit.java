package pl.lodz.p.it.viewports.book;

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
