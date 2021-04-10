package pl.lodz.p.it.applicationports.usecase.book;

public interface BookUsecaseSuit
    extends
        AddBookUsecase,
        GetAllBooksUsecase,
        GetBookUsecase,
        GetBookViaUUIDUsecase,
        RemoveBookUsecase,
        UpdateSingleBookUsecase

{
}
