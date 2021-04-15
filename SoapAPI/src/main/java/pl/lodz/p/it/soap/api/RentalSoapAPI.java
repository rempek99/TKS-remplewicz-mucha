package pl.lodz.p.it.soap.api;


import pl.lodz.p.it.soap.aggregates.adapters.RentalSoapAdapter;
import pl.lodz.p.it.soap.model.*;

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


    public BookRentalSoap getSingleBookRentalFromStorage(String str) throws SoapException {
        try {
            return rentalAdapter.getBookRentalViaUUID(str);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals(SoapException.NOT_FOUND))
                throw new SoapException(SoapException.NOT_FOUND);
            else
                throw e;
        }
    }


    public BookRentalSoap addSingleBookRentalToStorage(BookRentalSoap bookRental) {
        return rentalAdapter.addBookRental(bookRental);
    }


    public String removeSingleBookRentalFromStorage(String str) throws SoapException {
        BookRentalSoap bookRental = rentalAdapter.getBookRentalViaUUID(str);
        rentalAdapter.removeBookRental(bookRental);
        return SoapMessage.OK;
    }


    public String updateSingleBookRentalFromStorage(String str, BookRentalSoap desiredBookRental) throws SoapException {
        BookRentalSoap bookRentalToChange = rentalAdapter.getBookRentalViaUUID(str);
            rentalAdapter.updateSingleBookRental(bookRentalToChange, desiredBookRental);
        return SoapMessage.OK;
    }

    public List<MovieRentalSoap> getMoviesRentalsFromStorage() {
        return rentalAdapter.getAllMovieRentals();
    }


    public MovieRentalSoap getSingleMovieRentalFromStorage(String str) throws SoapException {
        try {
            return rentalAdapter.getMovieRentalViaUUID(str);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals(SoapException.NOT_FOUND))
                throw new SoapException(SoapException.NOT_FOUND);
            else
                throw e;
        }
    }


    public MovieRentalSoap addSingleMovieRentalToStorage(MovieRentalSoap movieRental) {
        return rentalAdapter.addMovieRental(movieRental);
    }

    public String removeSingleMovieRentalFromStorage(String str) throws SoapException {
        MovieRentalSoap movieRental = rentalAdapter.getMovieRentalViaUUID(str);
        rentalAdapter.removeMovieRental(movieRental);
        return SoapMessage.OK;
    }

    public String updateSingleMovieRentalFromStorage(String str, MovieRentalSoap desiredMovieRental) throws SoapException {
        MovieRentalSoap movieRentalToChange = rentalAdapter.getMovieRentalViaUUID(str);
            rentalAdapter.updateSingleMovieRental(movieRentalToChange, desiredMovieRental);
        return SoapMessage.OK;
    }
}
