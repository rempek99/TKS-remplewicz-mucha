package pl.lodz.p.it.rentapplicationapi.rentviewports.movie;

public interface GetMovieViaUUIDUsecase<M> {
    M getMovieViaUUID(String str);
}
