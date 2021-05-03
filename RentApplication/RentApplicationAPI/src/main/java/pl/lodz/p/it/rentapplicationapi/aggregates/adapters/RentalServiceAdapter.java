package pl.lodz.p.it.rentapplicationapi.aggregates.adapters;

import pl.lodz.p.it.rentapplicationapi.aggregates.converters.BookRentalConverter;
import pl.lodz.p.it.rentapplicationapi.aggregates.converters.MovieRentalConverter;
import pl.lodz.p.it.rentapplicationapi.exceptions.RestException;
import pl.lodz.p.it.rentapplicationapi.rentviewmodel.modelDTO.BookDTO;
import pl.lodz.p.it.rentapplicationapi.rentviewmodel.modelDTO.BookRentalDTO;
import pl.lodz.p.it.rentapplicationapi.rentviewmodel.modelDTO.MovieDTO;
import pl.lodz.p.it.rentapplicationapi.rentviewmodel.modelDTO.MovieRentalDTO;
import pl.lodz.p.it.rentapplicationapi.rentviewports.rentals.RentalViewPortUsecaseSuit;
import pl.lodz.p.it.rentapplicationcore.rentapplicationservice.services.RentalService;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Dependent
public class RentalServiceAdapter implements RentalViewPortUsecaseSuit<BookRentalDTO, MovieRentalDTO>, Serializable {

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
    public MovieRentalDTO addMovieRental(MovieRentalDTO r) {
        return MovieRentalConverter.convertMovieRentalToDTO(
                rentalService.addMovieRental(MovieRentalConverter.convertDTOToMovieRental(r))
        );
    }

    @Override
    public BookRentalDTO addBookRental(BookRentalDTO r) {
        return BookRentalConverter.convertBookRentalToDTO(
                rentalService.addBookRental(BookRentalConverter.convertDTOToBookRental(r))
        );
    }

    @Override
    public MovieRentalDTO getMovieRentalViaUUID(String str) throws RestException {
        if(rentalService.getMovieRentalViaUUID(str).isPresent())
            return MovieRentalConverter.convertMovieRentalToDTO(
                    rentalService.getMovieRentalViaUUID(str).get()
            );
        else
            throw new RestException(RestException.NOT_FOUND);
    }

    @Override
    public BookRentalDTO getBookRentalViaUUID(String str) throws RestException {
        if(rentalService.getBookRentalViaUUID(str).isPresent())
            return BookRentalConverter.convertBookRentalToDTO(
                rentalService.getBookRentalViaUUID(str).get()
        );
        else
            throw new RestException(RestException.NOT_FOUND);
    }

    @Override
    public MovieRentalDTO getMovieRental(MovieRentalDTO m) throws RestException {
        return getMovieRentalViaUUID(m.getId());
    }

    @Override
    public BookRentalDTO getBookRental(BookRentalDTO b) throws RestException {
        return getBookRentalViaUUID(b.getId());
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

    public void setBookRented(BookDTO book, boolean b) {
        book.setRented(b);
    }

    public void setMovieRented(MovieDTO movie, boolean b) {
        movie.setRented(b);
    }
}
