package services;

import infrastructure.RentalPort;
import model.BookRental;
import model.MovieRental;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SessionScoped
public class RentalService implements Serializable {
    @Inject
    private RentalPort rentalRepo;

    public List<MovieRental> getAllMovieRentals() { return rentalRepo.getMovieRentals(); }
    public List<BookRental> getAllBookRentals() { return rentalRepo.getBookRentals(); }

    public void addMovieRental(MovieRental r) { rentalRepo.addMovieRental(r); }
    public void addBookRental(BookRental r) { rentalRepo.addBookRental(r); }

    public void removeMovieRental(MovieRental r) {rentalRepo.removeMovieRental(r); }
    public void removeBookRental(BookRental r) {rentalRepo.removeBookRental(r); }

    public MovieRental getMovieRentalViaUUID(String str) {return rentalRepo.getMovieRentalViaUUID(str);}
    public BookRental getBookRentalViaUUID(String str) {return rentalRepo.getBookRentalViaUUID(str);}

    public void updateSingleBookRental(BookRental income, BookRental outcome) {rentalRepo.updateSingleBookRental(income, outcome);}

    public void updateSingleMovieRental(MovieRental income, MovieRental outcome) {rentalRepo.updateSingleMovieRental(income, outcome);}

    public List<Date> getDisabledDays() {return rentalRepo.getDisabledDays();}
}
