package pl.lodz.p.it.repositoriesadapters.aggregates.converters;

import pl.lodz.p.it.applicationcore.domainmodel.model.Account;
import pl.lodz.p.it.applicationcore.domainmodel.model.Movie;
import pl.lodz.p.it.applicationcore.domainmodel.model.MovieRental;
import pl.lodz.p.it.repositoriesadapters.model_ent.entities.AccountEnt;
import pl.lodz.p.it.repositoriesadapters.model_ent.entities.MovieEnt;
import pl.lodz.p.it.repositoriesadapters.model_ent.entities.MovieRentalEnt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovieRentalConverterTest {

    private final AccountEnt accountEnt = new AccountEnt("test", "Testowy", "user", true, "test", "test123");
    private final Account account= new Account("test", "Testowy", "user", true, "test", "test123");
    private final MovieEnt movieEnt = new MovieEnt("Test", "test", 7.9,false);
    private final Movie movie = new Movie("Test", "test", 7.9,false);
    private final MovieRentalEnt movieRentalEnt = new MovieRentalEnt(movieEnt, accountEnt);
    private final MovieRental movieRental = new MovieRental(movie, account);

    @BeforeEach
    void init() {
        accountEnt.setId("212c5de0-ad07-444a-9100-fd422b4bff93");
        account.setId("212c5de0-ad07-444a-9100-fd422b4bff93");
        movieEnt.setId("788e5f46-e7a0-4da3-98cc-9bd2d791698f");
        movie.setId("788e5f46-e7a0-4da3-98cc-9bd2d791698f");
        movieRentalEnt.setId("2dff585d-e208-4d47-a464-e4be9e0c6290");
        movieRental.setId("2dff585d-e208-4d47-a464-e4be9e0c6290");
    }

    @Test
    void convertMovieRentalToEnt() {
        MovieRentalEnt check = MovieRentalConverter.convertMovieRentalToEnt(movieRental);
        assertEquals(check, movieRentalEnt);
    }

    @Test
    void convertEntToMovieRental() {
        MovieRental check = MovieRentalConverter.convertEntToMovieRental(movieRentalEnt);
        assertEquals(check, movieRental);
    }

}