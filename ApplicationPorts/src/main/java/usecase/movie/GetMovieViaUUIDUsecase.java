package usecase.movie;

import model.Movie;

public interface GetMovieViaUUIDUsecase {
    Movie getViaUUID(String str);
}
