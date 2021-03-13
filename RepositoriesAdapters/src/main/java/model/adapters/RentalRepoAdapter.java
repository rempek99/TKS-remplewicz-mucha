package model.adapters;

import model.entities.*;
import infrastructure.*;
import model.*;

import javax.inject.Inject;
import java.util.*;

import static model.converters.MovieRentalConverter.convertMovieRentalToEnt;
import static model.converters.MovieRentalConverter.convertEntToMovieRental;
import static model.converters.BookRentalConverter.convertBookRentalToEnt;
import static model.converters.BookRentalConverter.convertEntToBookRental;

public class RentalRepoAdapter implements RentalPort{
    @Inject
    private RentalRepoEnt rentalRepo;

    private List<MovieRentalEnt> movieRentals = rentalRepo.getMovieRentals();
    private List<BookRentalEnt> bookRentals = rentalRepo.getBookRentals();
    private List<Date> invalidDates = new ArrayList<Date>();

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
