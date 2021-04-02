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

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static converters.BookRentalConverter.convertBookRentalToDTO;
import static converters.MovieRentalConverter.convertMovieRentalToDTO;

@Dependent
public class RentalRepoAdapter implements RentalUsecaseSuit, Serializable {


    private final RentalRepoDTO rentalRepo;
    private List<MovieRental> movieRentals;
    private List<BookRental> bookRentals;

    @Inject
    public RentalRepoAdapter(RentalRepoDTO rentalRepo) {
        this.rentalRepo = rentalRepo;
        cacheData();
    }

    private void cacheData() {
        movieRentals = rentalRepo.getMovieRentals()
                .stream()
                .map(MovieRentalConverter::convertDTOToMovieRental)
                .collect(Collectors.toList());
        bookRentals = rentalRepo.getBookRentals()
                .stream()
                .map(BookRentalConverter::convertDTOToBookRental)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieRentalDTO> getAllMovieRentals() {
        List<MovieRentalDTO> temp = Collections.synchronizedList(new ArrayList<MovieRentalDTO>());
        cacheData();
        for (MovieRental movieRental: movieRentals) {
            temp.add(convertMovieRentalToDTO(movieRental));
        }
        return temp;
    }

    @Override
    public List<BookRentalDTO> getAllBookRentals() {
        List<BookRentalDTO> temp = Collections.synchronizedList(new ArrayList<BookRentalDTO>());
        cacheData();
        for (BookRental bookRental: bookRentals) {
            temp.add(convertBookRentalToDTO(bookRental));
        }
        return temp;
    }

    @Override
    public void removeMovieRental(MovieRentalDTO r) {
        rentalRepo.removeMovieRental(convertMovieRentalToDTO(r));
    }

    @Override
    public void removeBookRental(BookRentalDTO r) {
        rentalRepo.removeBookRental(convertBookRentalToDTO(r));
    }

    @Override
    public void addMovieRental(MovieRentalDTO r) {
        rentalRepo.addMovieRental(convertMovieRentalToDTO(r));
    }

    @Override
    public void addBookRental(BookRentalDTO r) {
        rentalRepo.addBookRental(convertBookRentalToDTO(r));
    }

    @Override
    public MovieRentalDTO getMovieRentalViaUUID(String str) {
        cacheData();
        return movieRentals.stream()
                .filter(x -> x.getId().equals(str))
                .findFirst()
                .orElse(null);
    }

    @Override
    public BookRentalDTO getBookRentalViaUUID(String str) {
        cacheData();
        return bookRentals.stream()
                .filter(x -> x.getId().equals(str))
                .findFirst()
                .orElse(null);
    }

    @Override
    public MovieRentalDTO getMovieRental(MovieRentalDTO m) {
        return movieRentals.stream()
                .filter(x -> x.equals(m))
                .findFirst()
                .orElse(null);
    }

    @Override
    public BookRentalDTO getBookRental(BookRentalDTO b) {
        return bookRentals.stream()
                .filter(x -> x.equals(b))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void updateSingleMovieRental(MovieRentalDTO movieRentalToChange, MovieRentalDTO movieRentalWithData) {
        rentalRepo.updateSingleMovieRental(
                convertMovieRentalToDTO(movieRentalToChange),
                convertMovieRentalToDTO(movieRentalWithData)
        );
    }

    @Override
    public void updateSingleBookRental(BookRentalDTO bookRentalToChange, BookRentalDTO bookRentalWithData) {
        rentalRepo.updateSingleBookRental(
                convertBookRentalToDTO(bookRentalToChange),
                convertBookRentalToDTO(bookRentalWithData));
    }


    @Override
    public List<Date> getDisabledDays() {
        return rentalRepo.getDisabledDays();
    }
}
