package pl.lodz.p.it.topicmodels.events;

import pl.lodz.p.it.topicmodels.dtos.MovieDTO;

public class MovieEvent extends GeneralEvent {
    public static final String CREATE_MOVIE = "CreateMovie";
    public static final String REMOVE_MOVIE = "RemoveMovie";

    private MovieDTO object;

    public MovieEvent(String eventKey, MovieDTO object) {
        super(eventKey);
        this.object = object;
    }

    public static MovieEvent createMovieEvent(MovieDTO value) {
        return new MovieEvent(CREATE_MOVIE, value);
    }
}
