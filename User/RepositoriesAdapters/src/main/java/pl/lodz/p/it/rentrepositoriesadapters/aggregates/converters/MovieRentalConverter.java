package pl.lodz.p.it.rentrepositoriesadapters.aggregates.converters;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Account;
import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Movie;
import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.MovieRental;
import pl.lodz.p.it.rentrepositoriesadapters.model_ent.entities.AccountEnt;
import pl.lodz.p.it.rentrepositoriesadapters.model_ent.entities.MovieEnt;
import pl.lodz.p.it.rentrepositoriesadapters.model_ent.entities.MovieRentalEnt;

import java.util.Date;
import java.util.List;

public class MovieRentalConverter {

    private MovieRentalConverter() {

    }

    public static MovieRentalEnt convertMovieRentalToEnt(MovieRental movieRental) {
        Movie movie = movieRental.getMovie();
        MovieEnt movieEnt = MovieConverter.convertMovieToEnt(movie);
        Account account = movieRental.getAccount();
        AccountEnt accountEnt = AccountConverter.convertAccountToEnt(account);
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
        MovieEnt movieEnt = movieRental.getMovieEnt();
        Movie movie = MovieConverter.convertEntToMovie(movieEnt);
        AccountEnt accountEnt = movieRental.getAccountEnt();
        Account account = AccountConverter.convertEntToAccount(accountEnt);
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

