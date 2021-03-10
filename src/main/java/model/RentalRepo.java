package model;

import javax.enterprise.context.ApplicationScoped;
import java.util.*;

@ApplicationScoped
public class RentalRepo {
    private List<MovieRental> movieRentals = Collections.synchronizedList(new ArrayList<MovieRental>());
    private List<BookRental> bookRentals = Collections.synchronizedList(new ArrayList<BookRental>());
    private List<Date> invalidDates = new ArrayList<Date>();

    public List<MovieRental> getMovieRentals() {
        return movieRentals;
    }
    public List<BookRental> getBookRentals() {
        return bookRentals;
    }

    public void removeMovieRental(MovieRental r) {
            movieRentals.remove(r);
    }
    public void removeBookRental(BookRental r) {
            bookRentals.remove(r);
    }

    public void addMovieRental(MovieRental r) {
            movieRentals.add(r);
            r.setId(UUID.randomUUID().toString());
            printState();
    }

    public void addBookRental(BookRental r) {
            bookRentals.add(r);
            r.setId(UUID.randomUUID().toString());
            printState();
    }

    public MovieRental getMovieRentalViaUUID(String str) {
        for(MovieRental movie: movieRentals) {
            if(movie.getId().equals(str)){
                return movie;
            }
        }
        return null;
    }

    public BookRental getBookRentalViaUUID(String str) {
        for(BookRental book: bookRentals) {
            if(book.getId().equals(str)){
                return book;
            }
        }
        return null;
    }

    public MovieRental getMovieRental(MovieRental m) {
        if (movieRentals.contains(m)) {
            return m;
        } else {
            return null;
        }
    }

    public BookRental getBookRental(BookRental b) {
        if (bookRentals.contains(b)) {
            return b;
        } else {
            return null;
        }
    }

    public void updateSingleMovieRental(MovieRental movieRentalToChange, MovieRental movieRentalWithData) {
        MovieRental fromRepo = getMovieRental(movieRentalToChange);
        fromRepo.setId(movieRentalWithData.getId());
        fromRepo.setMovie(movieRentalWithData.getMovie());
        fromRepo.setAccount(movieRentalWithData.getAccount());
        fromRepo.setRange(movieRentalWithData.getRange());
        fromRepo.setRentalStart(movieRentalWithData.getRentalStart());
        fromRepo.setRentalEnd(movieRentalWithData.getRentalEnd());
    }

    public void updateSingleBookRental(BookRental bookRentalToChange, BookRental bookRentalWithData) {
        BookRental fromRepo = getBookRental(bookRentalToChange);
        fromRepo.setId(bookRentalWithData.getId());
        fromRepo.setBook(bookRentalWithData.getBook());
        fromRepo.setAccount(bookRentalWithData.getAccount());
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
