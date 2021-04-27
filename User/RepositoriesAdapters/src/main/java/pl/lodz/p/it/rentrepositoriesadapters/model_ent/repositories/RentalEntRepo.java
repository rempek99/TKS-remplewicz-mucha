package pl.lodz.p.it.rentrepositoriesadapters.model_ent.repositories;

import pl.lodz.p.it.rentrepositoriesadapters.model_ent.entities.*;

import javax.annotation.PostConstruct;
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

    @PostConstruct
    private void insertInitData() {
        addBookRental(new BookRentalEnt(
                new BookEnt("Doctor Sleep", "Stephen King", 656, false),
                new AccountEnt("Jan", "Kowalski", "USER", true, "jan12", "kowalski")
        ));
        addMovieRental(new MovieRentalEnt(
                new MovieEnt("The Godfather", "Francis Ford Coppola", 9.2, false),
                new AccountEnt("Jan2", "Kowalski2", "USER", true, "jan12", "kowalski")
        ));
    }

    public void removeMovieRental(MovieRentalEnt r) {
        for (Iterator<MovieRentalEnt> iterator = movieRentals.iterator(); iterator.hasNext(); ) {
            if (iterator.next().getId() == r.getId()) {
                iterator.remove();
            }
        }
    }

    public void removeBookRental(BookRentalEnt r) {
        for (Iterator<BookRentalEnt> iterator = bookRentals.iterator(); iterator.hasNext(); ) {
            if (iterator.next().getId() == r.getId()) {
                iterator.remove();
            }
        }
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
        for (MovieRentalEnt movie : movieRentals) {
            if (movie.getId().equals(str)) {
                return movie;
            }
        }
        return null;
    }

    public BookRentalEnt getBookRentalViaUUID(String str) {
        for (BookRentalEnt book : bookRentals) {
            if (book.getId().equals(str)) {
                return book;
            }
        }
        return null;
    }

    public MovieRentalEnt getMovieRental(MovieRentalEnt m) {
        return movieRentals.stream()
                .filter(x -> x.equals(m))
                .findFirst()
                .orElse(null);
    }

    public BookRentalEnt getBookRental(BookRentalEnt b) {
        return bookRentals.stream()
                .filter(x -> x.equals(b))
                .findFirst()
                .orElse(null);
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
