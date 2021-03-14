package model.repositories;

import model.entities.BookRentalEnt;
import model.entities.MovieRentalEnt;

import javax.enterprise.context.ApplicationScoped;
import java.util.*;

@ApplicationScoped
public class RentalRepo {
    private List<MovieRentalEnt> movieRentals = Collections.synchronizedList(new ArrayList<MovieRentalEnt>());
    private List<BookRentalEnt> bookRentals = Collections.synchronizedList(new ArrayList<BookRentalEnt>());
    private List<Date> invalidDates = new ArrayList<>();

    public List<MovieRentalEnt> getMovieRentals() {
        return movieRentals;
    }
    public List<BookRentalEnt> getBookRentals() {
        return bookRentals;
    }

    public void removeMovieRental(MovieRentalEnt r) {
            movieRentals.remove(r);
    }
    public void removeBookRental(BookRentalEnt r) {
            bookRentals.remove(r);
    }

    public void addMovieRental(MovieRentalEnt r) {
        r.setId(UUID.randomUUID().toString());
        movieRentals.add(r);
        printState();
    }

    public void addBookRental(BookRentalEnt r) {
        r.setId(UUID.randomUUID().toString());
        bookRentals.add(r);
        printState();
    }

    public MovieRentalEnt getMovieRentalViaUUID(String str) {
        for(MovieRentalEnt movie: movieRentals) {
            if(movie.getId().equals(str)){
                return movie;
            }
        }
        return null;
    }

    public BookRentalEnt getBookRentalViaUUID(String str) {
        for(BookRentalEnt book: bookRentals) {
            if(book.getId().equals(str)){
                return book;
            }
        }
        return null;
    }

    public MovieRentalEnt getMovieRental(MovieRentalEnt m) {
        if (movieRentals.contains(m)) {
            return m;
        } else {
            return null;
        }
    }

    public BookRentalEnt getBookRental(BookRentalEnt b) {
        if (bookRentals.contains(b)) {
            return b;
        } else {
            return null;
        }
    }

    public void updateSingleMovieRental(MovieRentalEnt movieRentalToChange, MovieRentalEnt movieRentalWithData) {
        MovieRentalEnt fromRepo = getMovieRental(movieRentalToChange);
        fromRepo.setId(movieRentalWithData.getId());
        fromRepo.setMovieEnt(movieRentalWithData.getMovieEnt());
        fromRepo.setAccountEnt(movieRentalWithData.getAccountEnt());
        fromRepo.setRange(movieRentalWithData.getRange());
        fromRepo.setRentalStart(movieRentalWithData.getRentalStart());
        fromRepo.setRentalEnd(movieRentalWithData.getRentalEnd());
    }

    public void updateSingleBookRental(BookRentalEnt bookRentalToChange, BookRentalEnt bookRentalWithData) {
        BookRentalEnt fromRepo = getBookRental(bookRentalToChange);
        fromRepo.setId(bookRentalWithData.getId());
        fromRepo.setBookEnt(bookRentalWithData.getBookEnt());
        fromRepo.setAccountEnt(bookRentalWithData.getAccountEnt());
        fromRepo.setRange(bookRentalWithData.getRange());
        fromRepo.setRentalStart(bookRentalWithData.getRentalStart());
        fromRepo.setRentalEnd(bookRentalWithData.getRentalEnd());
    }

    private void printState() {
        System.out.println(Arrays.toString(movieRentals.toArray()));
        System.out.println(Arrays.toString(bookRentals.toArray()));
    }

    public List<Date> getDisabledDays() {
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, -1);
        invalidDates.add(c.getTime());
        return invalidDates;
    }
}
