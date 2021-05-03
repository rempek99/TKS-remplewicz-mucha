package pl.lodz.p.it.rentviewports.movie;

public interface GetMovieViaUUIDUsecase<M> {
    M getMovieViaUUID(String str);
}
