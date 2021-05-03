package pl.lodz.p.it.rentapplicationapi.rentviewports.book;

public interface SetBookRentedUsecase<B> {
    void setBookRented(B b, boolean value);
}
