package model_ent.repositories;

import model_ent.entities.BookRentalEnt;
import model_ent.entities.MovieRentalEnt;

import javax.enterprise.context.ApplicationScoped;
import java.util.*;

@ApplicationScoped
public class RentalEntRepo {
    private List<MovieRentalEnt> movieRentals = Collections.synchronizedList(new ArrayList<MovieRentalEnt>());
    private List<BookRentalEnt> bookRentals = Collections.synchronizedList(new ArrayList<BookRentalEnt>());
    private List<Date> invalidDates = new ArrayList<>();

    public List<MovieRentalEnt> getMovieRentals() {
        return Collections.unmodifiableList(movieRentals);
    }
    public List<BookRentalEnt> getBookRentals() {
        return Collections.unmodifiableList(bookRentals);
    }

    public void removeMovieRental(MovieRentalEnt r) {
            movieRentals.remove(r);
    }
    public void removeBookRental(BookRentalEnt r) {
            bookRentals.remove(r);
    }

    public MovieRentalEnt addMovieRental(MovieRentalEnt r) {
        r.setId(UUID.randomUUID().toString());
        movieRentals.add(r);
//        printState();
        return r;
    }

    public BookRentalEnt addBookRental(BookRentalEnt r) {
        r.setId(UUID.randomUUID().toString());
        bookRentals.add(r);
//        printState();
        return r;
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

    public MovieRentalEnt updateSingleMovieRental(MovieRentalEnt movieRentalToChange, MovieRentalEnt movieRentalWithData) {
        MovieRentalEnt fromRepo = getMovieRental(movieRentalToChange);
        fromRepo.setMovieEnt(movieRentalWithData.getMovieEnt());
        fromRepo.setAccountEnt(movieRentalWithData.getAccountEnt());
        fromRepo.setRange(movieRentalWithData.getRange());
        fromRepo.setRentalStart(movieRentalWithData.getRentalStart());
        fromRepo.setRentalEnd(movieRentalWithData.getRentalEnd());
        return fromRepo;
    }

    public BookRentalEnt updateSingleBookRental(BookRentalEnt bookRentalToChange, BookRentalEnt bookRentalWithData) {
        BookRentalEnt fromRepo = getBookRental(bookRentalToChange);
        fromRepo.setBookEnt(bookRentalWithData.getBookEnt());
        fromRepo.setAccountEnt(bookRentalWithData.getAccountEnt());
        fromRepo.setRange(bookRentalWithData.getRange());
        fromRepo.setRentalStart(bookRentalWithData.getRentalStart());
        fromRepo.setRentalEnd(bookRentalWithData.getRentalEnd());
        return fromRepo;
    }

//    private void printState() {
//        System.out.println(Arrays.toString(movieRentals.toArray()));
//        System.out.println(Arrays.toString(bookRentals.toArray()));
//    }

    public List<Date> getDisabledDays() {
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, -1);
        invalidDates.add(c.getTime());
        return invalidDates;
    }
}
