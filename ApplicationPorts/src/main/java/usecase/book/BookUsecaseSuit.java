package usecase.book;

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
