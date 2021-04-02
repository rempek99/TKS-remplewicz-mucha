package movie;

import modelDTO.MovieDTO;

public interface GetMovieViaUUIDUsecase {
    MovieDTO getMovieViaUUID(String str);
}
