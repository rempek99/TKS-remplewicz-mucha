package dtoconverters;

import dtomodel.*;
import model.Account;
import model.Movie;
import model.MovieRental;

import java.util.Date;
import java.util.List;

public class MovieRentalDTOConverter {
    public static MovieRentalDTO convertMovieRentalToDTO(MovieRental movieRental) {
        Movie movie = movieRental.getMovie();
        MovieDTOConverter movieConverter = new MovieDTOConverter();
        MovieDTO movieDTO = movieConverter.convertMovieToDTO(movie);
        Account account = movieRental.getAccount();
        AccountDTOConverter accountConverter = new AccountDTOConverter();
        AccountDTO accountDTO = accountConverter.convertAccountToDTO(account);
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
        MovieDTO movieDTO = movieRental.getMovie();
        MovieDTOConverter movieConverter = new MovieDTOConverter();
        Movie movie = movieConverter.convertDTOToMovie(movieDTO);
        AccountDTO accountDTO = movieRental.getAccount();
        AccountDTOConverter accountConverter = new AccountDTOConverter();
        Account account = accountConverter.convertDTOToAccount(accountDTO);
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
