package pl.lodz.p.it.applicationcore.applicationservice.services;

import pl.lodz.p.it.applicationports.infrastructure.RentalPort;
import pl.lodz.p.it.applicationcore.domainmodel.model.BookRental;
import pl.lodz.p.it.applicationcore.domainmodel.model.MovieRental;
import pl.lodz.p.it.applicationports.usecase.rentals.RentalUsecaseSuit;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SessionScoped
public class RentalService implements Serializable, RentalUsecaseSuit {
    @Inject
    private RentalPort rentalRepo;

    public RentalService() {
    }

    public RentalService(RentalPort rentalRepo) {
        this.rentalRepo = rentalRepo;
    }

    @Override
    public List<MovieRental> getAllMovieRentals() { return rentalRepo.getMovieRentals(); }
    @Override
    public List<BookRental> getAllBookRentals() { return rentalRepo.getBookRentals(); }

    @Override
    public MovieRental addMovieRental(MovieRental r) {
        return rentalRepo.addMovieRental(r); }
    @Override
    public void addBookRental(BookRental r) { rentalRepo.addBookRental(r); }

    @Override
    public void removeMovieRental(MovieRental r) {rentalRepo.removeMovieRental(r); }
    @Override
    public void removeBookRental(BookRental r) {rentalRepo.removeBookRental(r); }

    @Override
    public MovieRental getMovieRentalViaUUID(String str) {return rentalRepo.getMovieRentalViaUUID(str);}
    @Override
    public BookRental getBookRentalViaUUID(String str) {return rentalRepo.getBookRentalViaUUID(str);}

    @Override
    public void updateSingleBookRental(BookRental income, BookRental outcome) {rentalRepo.updateSingleBookRental(income, outcome);}

    @Override
    public void updateSingleMovieRental(MovieRental income, MovieRental outcome) {rentalRepo.updateSingleMovieRental(income, outcome);}

    @Override
    public List<Date> getDisabledDays() {return rentalRepo.getDisabledDays();}
}

