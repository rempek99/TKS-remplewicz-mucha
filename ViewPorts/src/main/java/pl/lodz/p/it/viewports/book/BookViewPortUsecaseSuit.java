package pl.lodz.p.it.viewports.book;

public interface BookViewPortUsecaseSuit<B>
    extends
        AddBookUsecase<B>,
        GetAllBooksUsecase<B>,
        GetBookUsecase<B>,
        GetBookViaUUIDUsecase<B>,
        RemoveBookUsecase<B>,
        UpdateSingleBookUsecase<B>,
        SetBookRentedUsecase<B>
{
}
