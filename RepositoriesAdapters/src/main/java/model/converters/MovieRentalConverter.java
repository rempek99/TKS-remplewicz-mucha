package model.converters;

import model.entities.*;
import model.*;

import java.util.Date;
import java.util.List;

public class MovieRentalConverter {
    public static MovieRentalEnt convertMovieRentalToEnt(MovieRental movieRental) {
        Movie movie = movieRental.getMovie();
        MovieConverter movieConverter = new MovieConverter();
        MovieEnt movieEnt = movieConverter.convertMovieToEnt(movie);
        Account account = movieRental.getAccount();
        AccountConverter accountConverter = new AccountConverter();
        AccountEnt accountEnt = accountConverter.convertAccountToEnt(account);
        String id = movieRental.getId();
        List<Date> range = movieRental.getRange();
        Date rentalStart = movieRental.getRentalStart();
        Date rentalEnd = movieRental.getRentalEnd();

        MovieRentalEnt newMovieRental = new MovieRentalEnt(movieEnt, accountEnt);
        newMovieRental.setId(id);
        newMovieRental.setRange(range);
        newMovieRental.setRentalStart(rentalStart);
        newMovieRental.setRentalEnd(rentalEnd);

        return newMovieRental;
    }

    public static MovieRental convertEntToMovieRental(MovieRentalEnt movieRental) {
        MovieEnt movieEnt = movieRental.getMovie();
        MovieConverter movieConverter = new MovieConverter();
        Movie movie = movieConverter.convertEntToMovie(movieEnt);
        AccountEnt accountEnt = movieRental.getAccount();
        AccountConverter accountConverter = new AccountConverter();
        Account account = accountConverter.convertEntToAccount(accountEnt);
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

