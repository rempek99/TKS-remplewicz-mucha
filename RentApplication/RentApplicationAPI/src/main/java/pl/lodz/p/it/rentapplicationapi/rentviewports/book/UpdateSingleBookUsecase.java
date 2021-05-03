package pl.lodz.p.it.rentapplicationapi.rentviewports.book;


public interface UpdateSingleBookUsecase<B> {
    void updateSingleBook(B income, B outcome);
}
