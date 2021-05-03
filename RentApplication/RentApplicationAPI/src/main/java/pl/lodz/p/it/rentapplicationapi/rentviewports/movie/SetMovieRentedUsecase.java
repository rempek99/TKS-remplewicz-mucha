package pl.lodz.p.it.rentapplicationapi.rentviewports.movie;

public interface SetMovieRentedUsecase<M> {
    void setMovieRented(M m, boolean value);
}
