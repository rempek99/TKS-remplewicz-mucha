package usecase.movie;

import model.Movie;

public interface GetMovieViaUUIDUsecase {
    Movie getMovieViaUUID(String str);
}
