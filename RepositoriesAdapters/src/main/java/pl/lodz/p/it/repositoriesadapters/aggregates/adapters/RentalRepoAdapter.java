package pl.lodz.p.it.repositoriesadapters.aggregates.adapters;

import pl.lodz.p.it.repositoriesadapters.model_ent.repositories.RentalEntRepo;
import pl.lodz.p.it.repositoriesadapters.aggregates.converters.BookRentalConverter;
import pl.lodz.p.it.repositoriesadapters.aggregates.converters.MovieRentalConverter;
import pl.lodz.p.it.applicationports.infrastructure.RentalPort;
import pl.lodz.p.it.applicationcore.domainmodel.model.BookRental;
import pl.lodz.p.it.applicationcore.domainmodel.model.MovieRental;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static pl.lodz.p.it.repositoriesadapters.aggregates.converters.BookRentalConverter.convertBookRentalToEnt;
import static pl.lodz.p.it.repositoriesadapters.aggregates.converters.MovieRentalConverter.convertEntToMovieRental;
import static pl.lodz.p.it.repositoriesadapters.aggregates.converters.MovieRentalConverter.convertMovieRentalToEnt;

@Dependent
public class RentalRepoAdapter implements RentalPort, Serializable {


    private final RentalEntRepo rentalRepo;
    private List<MovieRental> movieRentals;
    private List<BookRental> bookRentals;

    @Inject
    public RentalRepoAdapter(RentalEntRepo rentalRepo) {
        this.rentalRepo = rentalRepo;
        cacheData();
    }

    private void cacheData() {
        movieRentals = rentalRepo.getMovieRentals()
                .stream()
                .map(MovieRentalConverter::convertEntToMovieRental)
                .collect(Collectors.toList());
        bookRentals = rentalRepo.getBookRentals()
                .stream()
                .map(BookRentalConverter::convertEntToBookRental)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieRental> getMovieRentals() {
        cacheData();
        return movieRentals;
    }

    @Override
    public List<BookRental> getBookRentals() {
        cacheData();
        return bookRentals;
    }

    @Override
    public void removeMovieRental(MovieRental r) {
        rentalRepo.removeMovieRental(convertMovieRentalToEnt(r));
    }

    @Override
    public void removeBookRental(BookRental r) {
        rentalRepo.removeBookRental(convertBookRentalToEnt(r));
    }

    @Override
    public MovieRental addMovieRental(MovieRental r) {

        return convertEntToMovieRental(rentalRepo.addMovieRental(convertMovieRentalToEnt(r)));
    }

    @Override
    public void addBookRental(BookRental r) {
        rentalRepo.addBookRental(convertBookRentalToEnt(r));
    }

    @Override
    public MovieRental getMovieRentalViaUUID(String str) {
        cacheData();
        return movieRentals.stream()
                .filter(x -> x.getId().equals(str))
                .findFirst()
                .orElse(null);
    }

    @Override
    public BookRental getBookRentalViaUUID(String str) {
        cacheData();
        return bookRentals.stream()
                .filter(x -> x.getId().equals(str))
                .findFirst()
                .orElse(null);
    }

    @Override
    public MovieRental getMovieRental(MovieRental m) {
        return movieRentals.stream()
                .filter(x -> x.equals(m))
                .findFirst()
                .orElse(null);
    }

    @Override
    public BookRental getBookRental(BookRental b) {
        return bookRentals.stream()
                .filter(x -> x.equals(b))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void updateSingleMovieRental(MovieRental movieRentalToChange, MovieRental movieRentalWithData) {
        rentalRepo.updateSingleMovieRental(
                convertMovieRentalToEnt(movieRentalToChange),
                convertMovieRentalToEnt(movieRentalWithData)
        );
    }

    @Override
    public void updateSingleBookRental(BookRental bookRentalToChange, BookRental bookRentalWithData) {
        rentalRepo.updateSingleBookRental(
                convertBookRentalToEnt(bookRentalToChange),
                convertBookRentalToEnt(bookRentalWithData));
    }


    @Override
    public List<Date> getDisabledDays() {
        return rentalRepo.getDisabledDays();
    }
}
