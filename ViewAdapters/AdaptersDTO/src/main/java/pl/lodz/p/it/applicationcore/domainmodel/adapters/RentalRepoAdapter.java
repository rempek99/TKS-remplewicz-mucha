package pl.lodz.p.it.applicationcore.domainmodel.adapters;

import pl.lodz.p.it.applicationcore.domainmodel.converters.MovieRentalConverter;
import pl.lodz.p.it.applicationcore.domainmodel.converters.BookRentalConverter;
import pl.lodz.p.it.viewmodel.modelDTO.BookRentalDTO;
import pl.lodz.p.it.viewmodel.modelDTO.MovieRentalDTO;
import pl.lodz.p.it.viewports.rentals.RentalUsecaseSuit;
import pl.lodz.p.it.applicationcore.applicationservice.services.RentalService;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Dependent
public class RentalRepoAdapter implements RentalUsecaseSuit, Serializable {

    private final RentalService rentalService;

    @Inject
    public RentalRepoAdapter(RentalService rentalService) {
        this.rentalService = rentalService;
    }


    @Override
    public List<MovieRentalDTO> getAllMovieRentals() {
        List<MovieRentalDTO> movieRentalDTOS = new ArrayList<>();
        rentalService
                .getAllMovieRentals()
                .forEach(
                        r -> movieRentalDTOS.add(getMovieRentalViaUUID(r.getId()))
                );
        return movieRentalDTOS;
    }

    @Override
    public List<BookRentalDTO> getAllBookRentals() {
        List<BookRentalDTO> bookRentalDTOS = new ArrayList<>();
        rentalService
                .getAllBookRentals()
                .forEach(
                        r -> bookRentalDTOS.add(getBookRentalViaUUID(r.getId()))
                );
        return bookRentalDTOS;
    }

    @Override
    public void removeMovieRental(MovieRentalDTO r) {
        rentalService.removeMovieRental(MovieRentalConverter.convertDTOToMovieRental(r));
    }

    @Override
    public void removeBookRental(BookRentalDTO r) {
        rentalService.removeBookRental(BookRentalConverter.convertDTOToBookRental(r));
    }

    @Override
    public void addMovieRental(MovieRentalDTO r) {
        rentalService.addMovieRental(MovieRentalConverter.convertDTOToMovieRental(r));
    }

    @Override
    public void addBookRental(BookRentalDTO r) {
        rentalService.addBookRental(BookRentalConverter.convertDTOToBookRental(r));
    }

    @Override
    public MovieRentalDTO getMovieRentalViaUUID(String str) {
        return MovieRentalConverter.convertMovieRentalToDTO(rentalService.getMovieRentalViaUUID(str));
    }

    @Override
    public BookRentalDTO getBookRentalViaUUID(String str) {
        return BookRentalConverter.convertBookRentalToDTO(rentalService.getBookRentalViaUUID(str));
    }

    @Override
    public MovieRentalDTO getMovieRental(MovieRentalDTO m) {
        return MovieRentalConverter.convertMovieRentalToDTO(rentalService.getMovieRentalViaUUID(m.getId()));
    }

    @Override
    public BookRentalDTO getBookRental(BookRentalDTO b) {
        return BookRentalConverter.convertBookRentalToDTO(rentalService.getBookRentalViaUUID(b.getId()));
    }

    @Override
    public void updateSingleMovieRental(
            MovieRentalDTO movieRentalToChange,
            MovieRentalDTO movieRentalWithData) {
        rentalService
                .updateSingleMovieRental(
                        MovieRentalConverter.convertDTOToMovieRental(movieRentalToChange),
                        MovieRentalConverter.convertDTOToMovieRental(movieRentalWithData));
    }

    @Override
    public void updateSingleBookRental(
            BookRentalDTO bookRentalToChange,
            BookRentalDTO bookRentalWithData) {
        rentalService
                .updateSingleBookRental(
                        BookRentalConverter.convertDTOToBookRental(bookRentalToChange),
                        BookRentalConverter.convertDTOToBookRental(bookRentalWithData));
    }


    @Override
    public List<Date> getDisabledDays() {
        return rentalService.getDisabledDays();
    }
}
