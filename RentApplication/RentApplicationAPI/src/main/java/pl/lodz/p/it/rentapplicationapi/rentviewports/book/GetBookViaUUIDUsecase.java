package pl.lodz.p.it.rentapplicationapi.rentviewports.book;


public interface GetBookViaUUIDUsecase<B> {
    B getBookViaUUID(String str);
}
