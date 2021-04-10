package pl.lodz.p.it.applicationcore.domainmodel.converters;

import pl.lodz.p.it.applicationcore.domainmodel.model.Account;
import pl.lodz.p.it.applicationcore.domainmodel.model.Movie;
import pl.lodz.p.it.applicationcore.domainmodel.model.MovieRental;
import pl.lodz.p.it.viewmodel.modelDTO.AccountDTO;
import pl.lodz.p.it.viewmodel.modelDTO.MovieDTO;
import pl.lodz.p.it.viewmodel.modelDTO.MovieRentalDTO;

import java.util.Date;
import java.util.List;

public class MovieRentalConverter {

    private MovieRentalConverter() {
    }

    public static MovieRentalDTO convertMovieRentalToDTO(MovieRental movieRental) {
        Movie movie = movieRental.getMovie();
        MovieDTO movieDTO = MovieConverter.convertMovieToDTO(movie);
        Account account = movieRental.getAccount();
        AccountDTO accountDTO = AccountConverter.convertAccountToDTO(account);
        String id = movieRental.getId();
        List<Date> range = movieRental.getRange();
        Date rentalStart = movieRental.getRentalStart();
        Date rentalEnd = movieRental.getRentalEnd();

        MovieRentalDTO newMovieRental = new MovieRentalDTO(movieDTO, accountDTO);
        newMovieRental.setId(id);
        newMovieRental.setRange(range);
        newMovieRental.setRentalStart(rentalStart);
        newMovieRental.setRentalEnd(rentalEnd);

        return newMovieRental;
    }

    public static MovieRental convertDTOToMovieRental(MovieRentalDTO movieRental) {
        MovieDTO movieDTO = movieRental.getMovieDTO();
        Movie movie = MovieConverter.convertDTOToMovie(movieDTO);
        AccountDTO accountDTO = movieRental.getAccountDTO();
        Account account = AccountConverter.convertDTOToAccount(accountDTO);
        String id = movieRental.getId();
        List<Date> range = movieRental.getRange();
        Date rentalStart = movieRental.getRentalStart();
        Date rentalEnd = movieRental.getRentalEnd();

        MovieRental newMovieRental = new MovieRental(movie, account);
        newMovieRental.setId(id);
        newMovieRental.setRange(range);
        newMovieRental.setRentalStart(rentalStart);
        newMovieRental.setRentalEnd(rentalEnd);

        return newMovieRental;
    }
}