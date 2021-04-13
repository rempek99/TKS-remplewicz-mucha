package pl.lodz.p.it.soap.api;


import pl.lodz.p.it.soap.aggregates.adapters.RentalSoapAdapter;
import pl.lodz.p.it.soap.model.BookRentalSoap;
import pl.lodz.p.it.soap.model.MovieRentalSoap;

import javax.inject.Inject;
import javax.jws.WebService;
import java.util.List;
import java.util.Optional;

@WebService(serviceName = "RentalAPI")
public class RentalSoapAPI {

    @Inject
    RentalSoapAdapter rentalAdapter;


    public List<BookRentalSoap> getBooksRentalsFromStorage() {
        return rentalAdapter.getAllBookRentals();
    }


    public void addSingleBookRentalToStorage(BookRentalSoap bookRental) {
        rentalAdapter.addBookRental(bookRental);
    }


    public void removeSingleBookRentalFromStorage(String str) {
        Optional<BookRentalSoap> bookRental = Optional.ofNullable(rentalAdapter.getBookRentalViaUUID(str));
        if (bookRental.isPresent()) {
            rentalAdapter.removeBookRental(bookRental.get());
        } else {
            throw new IllegalArgumentException("Book not found");
        }
    }


    public void updateSingleBookRentalFromStorage(String str, BookRentalSoap desiredBookRental) {
        Optional<BookRentalSoap> bookRentalToChange = Optional.ofNullable(rentalAdapter.getBookRentalViaUUID(str));
        if (bookRentalToChange.isPresent()) {
            rentalAdapter.updateSingleBookRental(bookRentalToChange.get(), desiredBookRental);
        } else {
            throw new IllegalArgumentException("Book not found");
        }
    }

    public List<MovieRentalSoap> getMoviesRentalsFromStorage() {
        return rentalAdapter.getAllMovieRentals();
    }


    public void addSingleMovieRentalToStorage(MovieRentalSoap movieRental) {
        rentalAdapter.addMovieRental(movieRental);
    }

    public void removeSingleMovieRentalFromStorage(String str) {
        Optional<MovieRentalSoap> movieRental = Optional.ofNullable(rentalAdapter.getMovieRentalViaUUID(str));
        if (movieRental.isPresent()) {
            rentalAdapter.removeMovieRental(movieRental.get());
        } else {
            throw new IllegalArgumentException("Movie not found");
        }
    }

    public void updateSingleMovieRentalFromStorage(String str, MovieRentalSoap desiredMovieRental) {
        Optional<MovieRentalSoap> movieRentalToChange = Optional.ofNullable(rentalAdapter.getMovieRentalViaUUID(str));
        if (movieRentalToChange.isPresent()) {
            rentalAdapter.updateSingleMovieRental(movieRentalToChange.get(), desiredMovieRental);
        } else {
            throw new IllegalArgumentException("Movie not found");
        }
    }
}
