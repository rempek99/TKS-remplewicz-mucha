package pl.lodz.p.it.viewadapters.adapters;

import pl.lodz.p.it.applicationcore.applicationservice.services.RentalService;
import pl.lodz.p.it.viewadapters.converters.BookRentalConverter;
import pl.lodz.p.it.viewadapters.converters.MovieRentalConverter;
import pl.lodz.p.it.viewmodel.modelDTO.BookRentalDTO;
import pl.lodz.p.it.viewmodel.modelDTO.MovieRentalDTO;
import pl.lodz.p.it.viewports.rentals.RentalViewPortUsecaseSuit;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Dependent
public class RentalServiceAdapter implements RentalViewPortUsecaseSuit<BookRentalDTO,MovieRentalDTO>, Serializable {

    private final RentalService rentalService;

    @Inject
    public RentalServiceAdapter(RentalService rentalService) {
        this.rentalService = rentalService;
    }


    @Override
    public List<MovieRentalDTO> getAllMovieRentals() {
        return rentalService
                .getAllMovieRentals()
                .stream()
                .map(MovieRentalConverter::convertMovieRentalToDTO)
                .collect(Collectors.toList());

    }

    @Override
    public List<BookRentalDTO> getAllBookRentals() {
        return rentalService
                .getAllBookRentals()
                .stream()
                .map(BookRentalConverter::convertBookRentalToDTO)
                .collect(Collectors.toList());

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
