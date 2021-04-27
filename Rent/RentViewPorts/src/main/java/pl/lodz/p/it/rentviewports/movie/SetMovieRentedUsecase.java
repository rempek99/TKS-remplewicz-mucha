package pl.lodz.p.it.rentviewports.movie;

public interface SetMovieRentedUsecase<M> {
    void setMovieRented(M m, boolean value);
}
