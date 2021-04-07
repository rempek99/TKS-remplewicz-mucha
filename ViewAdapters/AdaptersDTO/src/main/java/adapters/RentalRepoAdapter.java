package adapters;

import converters.BookRentalConverter;
import converters.MovieRentalConverter;
import model.BookRental;
import modelDTO.BookRentalDTO;
import model.MovieRental;
import modelDTO.MovieDTO;
import modelDTO.MovieRentalDTO;
import rentals.RentalUsecaseSuit;
import repositoriesDTO.RentalRepoDTO;
import services.RentalService;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Dependent
public class RentalRepoAdapter implements RentalUsecaseSuit, Serializable {

    private RentalService rentalService;
    private BookRentalConverter bookRentalConverter;
    private MovieRentalConverter movieRentalConverter;

    @Inject
    public RentalRepoAdapter(RentalService rentalService) {
        this.rentalService = rentalService;
        bookRentalConverter = new BookRentalConverter();
        movieRentalConverter = new MovieRentalConverter();
    }


    @Override
    public List<MovieRentalDTO> getAllMovieRentals() {
        List<MovieRentalDTO> movieRentalDTOS = new ArrayList<>();
        rentalService.getAllMovieRentals().forEach(r -> movieRentalDTOS.add(getMovieRentalViaUUID(r.getId())));
        return movieRentalDTOS;
    }

    @Override
    public List<BookRentalDTO> getAllBookRentals() {
        List<BookRentalDTO> bookRentalDTOS = new ArrayList<>();
        rentalService.getAllBookRentals().forEach(r -> bookRentalDTOS.add(getBookRentalViaUUID(r.getId())));
        return bookRentalDTOS;
    }

    @Override
    public void removeMovieRental(MovieRentalDTO r) {
        rentalService.removeMovieRental(movieRentalConverter.convertDTOToMovieRental(r));
    }

    @Override
    public void removeBookRental(BookRentalDTO r) {
        rentalService.removeBookRental(bookRentalConverter.convertDTOToBookRental(r));
    }

    @Override
    public void addMovieRental(MovieRentalDTO r) {
        rentalService.addMovieRental(movieRentalConverter.convertDTOToMovieRental(r));
    }

    @Override
    public void addBookRental(BookRentalDTO r) {
        rentalService.addBookRental(bookRentalConverter.convertDTOToBookRental(r));
    }

    @Override
    public MovieRentalDTO getMovieRentalViaUUID(String str) {
        return movieRentalConverter.convertMovieRentalToDTO(rentalService.getMovieRentalViaUUID(str));
    }

    @Override
    public BookRentalDTO getBookRentalViaUUID(String str) {
        return bookRentalConverter.convertBookRentalToDTO(rentalService.getBookRentalViaUUID(str));
    }

    @Override
    public MovieRentalDTO getMovieRental(MovieRentalDTO m) {
        return movieRentalConverter.convertMovieRentalToDTO(rentalService.getMovieRentalViaUUID(m.getId()));
    }

    @Override
    public BookRentalDTO getBookRental(BookRentalDTO b) {
        return bookRentalConverter.convertBookRentalToDTO(rentalService.getBookRentalViaUUID(b.getId()));
    }

    @Override
    public void updateSingleMovieRental(MovieRentalDTO movieRentalToChange, MovieRentalDTO movieRentalWithData) {
        rentalService.updateSingleMovieRental(movieRentalConverter.convertDTOToMovieRental(movieRentalToChange), movieRentalConverter.convertDTOToMovieRental(movieRentalWithData));
    }

    @Override
    public void updateSingleBookRental(BookRentalDTO bookRentalToChange, BookRentalDTO bookRentalWithData) {
        rentalService.updateSingleBookRental(bookRentalConverter.convertDTOToBookRental(bookRentalToChange), bookRentalConverter.convertDTOToBookRental(bookRentalWithData));
    }


    @Override
    public List<Date> getDisabledDays() {
        return rentalService.getDisabledDays();
    }
}
