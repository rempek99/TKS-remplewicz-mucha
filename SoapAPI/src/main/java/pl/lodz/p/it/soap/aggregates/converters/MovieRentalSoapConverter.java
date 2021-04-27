package pl.lodz.p.it.soap.aggregates.converters;

import pl.lodz.p.it.soap.model.MovieRentalSoap;

public class MovieRentalSoapConverter {

    private MovieRentalSoapConverter() {

    }

    public static MovieRentalSoap convertMovieRentalToMovieRentalSoap(MovieRental movieRental) {

        MovieRentalSoap newMovieRental = new MovieRentalSoap(
                MovieSoapConverter.convertMovieToMovieSoap(movieRental.getMovie()),
                AccountSoapConverter.convertAccountToAccountSoap(movieRental.getAccount())
        );
        newMovieRental.setId(movieRental.getId());
        newMovieRental.setRange(movieRental.getRange());
        newMovieRental.setRentalStart(movieRental.getRentalStart());
        newMovieRental.setRentalEnd(movieRental.getRentalEnd());
        return newMovieRental;
    }

    public static MovieRental convertMovieRentalSoapToMovieRental(MovieRentalSoap movieRental) {
        MovieRental newMovieRental = new MovieRental(
                MovieSoapConverter.convertMovieSoapToMovie(movieRental.getMovie()),
                AccountSoapConverter.convertAccountSoapToAccount(movieRental.getAccount())
        );
        newMovieRental.setId(movieRental.getId());
        newMovieRental.setRange(movieRental.getRange());
        newMovieRental.setRentalStart(movieRental.getRentalStart());
        newMovieRental.setRentalEnd(movieRental.getRentalEnd());
        return newMovieRental;
    }
}

