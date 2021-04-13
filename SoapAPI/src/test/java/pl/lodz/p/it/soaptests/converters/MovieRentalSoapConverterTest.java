package pl.lodz.p.it.soaptests.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.lodz.p.it.applicationcore.domainmodel.model.Account;
import pl.lodz.p.it.applicationcore.domainmodel.model.Movie;
import pl.lodz.p.it.applicationcore.domainmodel.model.MovieRental;
import pl.lodz.p.it.soap.aggregates.converters.MovieRentalSoapConverter;
import pl.lodz.p.it.soap.model.AccountSoap;
import pl.lodz.p.it.soap.model.MovieRentalSoap;
import pl.lodz.p.it.soap.model.MovieSoap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovieRentalSoapConverterTest {

    private final AccountSoap accountSoap = new AccountSoap("test", "Testowy", "user", true, "test", "test123");
    private final Account account= new Account("test", "Testowy", "user", true, "test", "test123");
    private final MovieSoap movieSoap = new MovieSoap("Test", "test", 7.9,false);
    private final Movie movie = new Movie("Test", "test", 7.9,false);
    private final MovieRentalSoap movieRentalSoap = new MovieRentalSoap(movieSoap, accountSoap);
    private final MovieRental movieRental = new MovieRental(movie, account);

    @BeforeEach
    void init() {
        accountSoap.setId("212c5de0-ad07-444a-9100-fd422b4bff93");
        account.setId("212c5de0-ad07-444a-9100-fd422b4bff93");
        movieSoap.setId("788e5f46-e7a0-4da3-98cc-9bd2d791698f");
        movie.setId("788e5f46-e7a0-4da3-98cc-9bd2d791698f");
        movieRentalSoap.setId("2dff585d-e208-4d47-a464-e4be9e0c6290");
        movieRental.setId("2dff585d-e208-4d47-a464-e4be9e0c6290");
    }

    @Test
    void convertMovieRentalToSoap() {
        MovieRentalSoap check = MovieRentalSoapConverter.convertMovieRentalToMovieRentalSoap(movieRental);
        assertEquals(check, movieRentalSoap);
    }

    @Test
    void convertSoapToMovieRental() {
        MovieRental check = MovieRentalSoapConverter.convertMovieRentalSoapToMovieRental(movieRentalSoap);
        assertEquals(check, movieRental);
    }

}