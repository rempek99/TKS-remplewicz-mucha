package aggregates.adapters;

import infrastructure.RentalPort;
import model.BookRental;
import model.MovieRental;
import model_ent.entities.BookRentalEnt;
import model_ent.entities.MovieRentalEnt;
import model_ent.repositories.RentalEntRepo;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static aggregates.converters.BookRentalConverter.convertBookRentalToEnt;
import static aggregates.converters.BookRentalConverter.convertEntToBookRental;
import static aggregates.converters.MovieRentalConverter.convertEntToMovieRental;
import static aggregates.converters.MovieRentalConverter.convertMovieRentalToEnt;

@Dependent
public class RentalRepoAdapter implements RentalPort, Serializable {


    private final RentalEntRepo rentalRepo;
    private List<MovieRentalEnt> movieRentals;
    private List<BookRentalEnt> bookRentals;

    @Inject
    public RentalRepoAdapter(RentalEntRepo rentalRepo) {
        this.rentalRepo = rentalRepo;
        movieRentals = rentalRepo.getMovieRentals();
        bookRentals = rentalRepo.getBookRentals();
    }

    @Override
    public List<MovieRental> getMovieRentals() {
        List<MovieRental> temp = Collections.synchronizedList(new ArrayList<MovieRental>());
        for (MovieRentalEnt movieRental: movieRentals) {
            temp.add(convertEntToMovieRental(movieRental));
        }
        return temp;
    }

    @Override
    public List<BookRental> getBookRentals() {
        List<BookRental> temp = Collections.synchronizedList(new ArrayList<BookRental>());
        for (BookRentalEnt bookRental: bookRentals) {
            temp.add(convertEntToBookRental(bookRental));
        }
        return temp;
    }

    @Override
    public void removeMovieRental(MovieRental r) {
        rentalRepo.removeMovieRental(convertMovieRentalToEnt(r));
    }

    @Override
    public void removeBookRental(BookRental r){
        rentalRepo.removeBookRental(convertBookRentalToEnt(r));
    }

    @Override
    public void addMovieRental(MovieRental r) {

        rentalRepo.addMovieRental(convertMovieRentalToEnt(r));
    }

    @Override
    public void addBookRental(BookRental r) {
        rentalRepo.addBookRental(convertBookRentalToEnt(r));
    }

    @Override
    public MovieRental getMovieRentalViaUUID(String str) {
        for(MovieRentalEnt movie: movieRentals) {
            if(movie.getId().equals(str)){
                return convertEntToMovieRental(movie);
            }
        }
        return null;
    }

    @Override
    public BookRental getBookRentalViaUUID(String str) {
        for(BookRentalEnt book: bookRentals) {
            if(book.getId().equals(str)){
                return convertEntToBookRental(book);
            }
        }
        return null;
    }

    @Override
    public MovieRental getMovieRental(MovieRental m) {
        if (movieRentals.contains(m)) {
            return m;
        } else {
            return null;
        }
    }

    @Override
    public BookRental getBookRental(BookRental b) {
        if (bookRentals.contains(b)) {
            return b;
        } else {
            return null;
        }
    }

    @Override
    public void updateSingleMovieRental(MovieRental movieRentalToChange, MovieRental movieRentalWithData) {
        MovieRental fromRepo = getMovieRental(movieRentalToChange);
        fromRepo.setId(movieRentalWithData.getId());
        fromRepo.setMovie(movieRentalWithData.getMovie());
        fromRepo.setAccount(movieRentalWithData.getAccount());
        fromRepo.setRange(movieRentalWithData.getRange());
        fromRepo.setRentalStart(movieRentalWithData.getRentalStart());
        fromRepo.setRentalEnd(movieRentalWithData.getRentalEnd());
    }

    @Override
    public void updateSingleBookRental(BookRental bookRentalToChange, BookRental bookRentalWithData) {
        BookRental fromRepo = getBookRental(bookRentalToChange);
        fromRepo.setId(bookRentalWithData.getId());
        fromRepo.setBook(bookRentalWithData.getBook());
        fromRepo.setAccount(bookRentalWithData.getAccount());
        fromRepo.setRange(bookRentalWithData.getRange());
        fromRepo.setRentalStart(bookRentalWithData.getRentalStart());
        fromRepo.setRentalEnd(bookRentalWithData.getRentalEnd());
    }


    @Override
    public List<Date> getDisabledDays() {
        return rentalRepo.getDisabledDays();
    }
}
