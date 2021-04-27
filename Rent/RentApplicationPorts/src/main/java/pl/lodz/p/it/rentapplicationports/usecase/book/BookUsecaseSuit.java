package pl.lodz.p.it.rentapplicationports.usecase.book;

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
