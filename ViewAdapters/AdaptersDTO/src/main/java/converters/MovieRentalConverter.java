package converters;

import model.Account;
import model.Movie;
import model.MovieRental;
import modelDTO.AccountDTO;
import modelDTO.MovieDTO;
import modelDTO.MovieRentalDTO;

import java.util.Date;
import java.util.List;

public class MovieRentalConverter {

    public MovieRentalConverter() {
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