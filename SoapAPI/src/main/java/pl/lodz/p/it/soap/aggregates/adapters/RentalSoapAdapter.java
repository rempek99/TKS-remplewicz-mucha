package pl.lodz.p.it.soap.aggregates.adapters;

import pl.lodz.p.it.applicationcore.applicationservice.services.RentalService;
import pl.lodz.p.it.applicationports.usecase.rentals.RentalUsecaseSuit;
import pl.lodz.p.it.soap.aggregates.converters.BookRentalSoapConverter;
import pl.lodz.p.it.soap.aggregates.converters.MovieRentalSoapConverter;
import pl.lodz.p.it.soap.model.BookRentalSoap;
import pl.lodz.p.it.soap.model.MovieRentalSoap;
import pl.lodz.p.it.soap.model.SoapException;
import pl.lodz.p.it.viewports.rentals.RentalViewPortUsecaseSuit;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Dependent
public class RentalSoapAdapter implements RentalViewPortUsecaseSuit<BookRentalSoap, MovieRentalSoap>, Serializable {

    private final RentalUsecaseSuit rentalService;

    @Inject
    public RentalSoapAdapter(RentalService rentalService) {
        this.rentalService = rentalService;
    }


    @Override
    public List<MovieRentalSoap> getAllMovieRentals() {
        return rentalService
                .getAllMovieRentals()
                .stream()
                .map(MovieRentalSoapConverter::convertMovieRentalToMovieRentalSoap)
                .collect(Collectors.toList());

    }

    @Override
    public List<BookRentalSoap> getAllBookRentals() {
        return rentalService
                .getAllBookRentals()
                .stream()
                .map(BookRentalSoapConverter::convertBookRentalToBookRentalSoap)
                .collect(Collectors.toList());

    }

    @Override
    public void removeMovieRental(MovieRentalSoap r) {
        rentalService.removeMovieRental(MovieRentalSoapConverter.convertMovieRentalSoapToMovieRental(r));
    }

    @Override
    public void removeBookRental(BookRentalSoap r) {
        rentalService.removeBookRental(BookRentalSoapConverter.convertBookRentalSoapToBookRental(r));
    }

    @Override
    public MovieRentalSoap addMovieRental(MovieRentalSoap r) {
        return MovieRentalSoapConverter.convertMovieRentalToMovieRentalSoap(
                rentalService.addMovieRental(MovieRentalSoapConverter.convertMovieRentalSoapToMovieRental(r))
        );
    }

    @Override
    public BookRentalSoap addBookRental(BookRentalSoap r) {
        return BookRentalSoapConverter.convertBookRentalToBookRentalSoap(
                rentalService.addBookRental(BookRentalSoapConverter.convertBookRentalSoapToBookRental(r))
        );
    }

    @Override
    public MovieRentalSoap getMovieRentalViaUUID(String str) throws SoapException {
        if(rentalService.getMovieRentalViaUUID(str).isPresent())
        return MovieRentalSoapConverter.convertMovieRentalToMovieRentalSoap(
                rentalService.getMovieRentalViaUUID(str).get());
        else
            throw new SoapException(SoapException.NOT_FOUND);
    }

    @Override
    public BookRentalSoap getBookRentalViaUUID(String str) throws SoapException {
        if(rentalService.getBookRentalViaUUID(str).isPresent())
        return BookRentalSoapConverter.convertBookRentalToBookRentalSoap(
                rentalService.getBookRentalViaUUID(str).get());
        else
            throw new SoapException(SoapException.NOT_FOUND);
    }

    @Override
    public MovieRentalSoap getMovieRental(MovieRentalSoap m) throws SoapException {
        return getMovieRentalViaUUID(m.getId());
    }

    @Override
    public BookRentalSoap getBookRental(BookRentalSoap b) throws SoapException {
        return getBookRentalViaUUID(b.getId());
    }

    @Override
    public void updateSingleMovieRental(
            MovieRentalSoap movieRentalToChange,
            MovieRentalSoap movieRentalWithData) {
        rentalService
                .updateSingleMovieRental(
                        MovieRentalSoapConverter.convertMovieRentalSoapToMovieRental(movieRentalToChange),
                        MovieRentalSoapConverter.convertMovieRentalSoapToMovieRental(movieRentalWithData));
    }

    @Override
    public void updateSingleBookRental(
            BookRentalSoap bookRentalToChange,
            BookRentalSoap bookRentalWithData) {
        rentalService
                .updateSingleBookRental(
                        BookRentalSoapConverter.convertBookRentalSoapToBookRental(bookRentalToChange),
                        BookRentalSoapConverter.convertBookRentalSoapToBookRental(bookRentalWithData));
    }


    @Override
    public List<Date> getDisabledDays() {
        return rentalService.getDisabledDays();
    }
}
