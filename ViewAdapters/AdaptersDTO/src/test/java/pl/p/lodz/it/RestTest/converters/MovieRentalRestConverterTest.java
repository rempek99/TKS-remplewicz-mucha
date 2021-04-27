package pl.p.lodz.it.RestTest.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Account;
import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Movie;
import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.MovieRental;
import pl.lodz.p.it.viewadapters.converters.MovieRentalConverter;
import pl.lodz.p.it.viewmodel.modelDTO.AccountDTO;
import pl.lodz.p.it.viewmodel.modelDTO.MovieDTO;
import pl.lodz.p.it.viewmodel.modelDTO.MovieRentalDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovieRentalRestConverterTest {

    private final AccountDTO accountDTO = new AccountDTO("test", "Testowy", "user", true, "test", "test123");
    private final MovieDTO movieDTO = new MovieDTO("Test", "test", 7.9,false);
    private final MovieRentalDTO movieRentalDTO = new MovieRentalDTO(movieDTO, accountDTO);
    private final Movie movie = new Movie("Test", "test", 7.9,false);
    private final Account account= new Account("test", "Testowy", "user", true, "test", "test123");
    private final MovieRental movieRental = new MovieRental(movie, account);

    @BeforeEach
    void init() {
        accountDTO.setId("212c5de0-ad07-444a-9100-fd422b4bff93");
        account.setId("212c5de0-ad07-444a-9100-fd422b4bff93");
        movieDTO.setId("788e5f46-e7a0-4da3-98cc-9bd2d791698f");
        movie.setId("788e5f46-e7a0-4da3-98cc-9bd2d791698f");
        movieRentalDTO.setId("2dff585d-e208-4d47-a464-e4be9e0c6290");
        movieRental.setId("2dff585d-e208-4d47-a464-e4be9e0c6290");
    }

    @Test
    void convertMovieRentalToDTO() {
        MovieRentalDTO check = MovieRentalConverter.convertMovieRentalToDTO(movieRental);
        assertEquals(check, movieRentalDTO);
    }

    @Test
    void convertDTOToMovieRental() {
        MovieRental check = MovieRentalConverter.convertDTOToMovieRental(movieRentalDTO);
        assertEquals(check, movieRental);
    }

}