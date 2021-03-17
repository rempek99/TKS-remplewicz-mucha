package controller;

import lombok.Getter;
import lombok.Setter;
import model.*;
import services.AccountService;
import services.RentalService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Named
@RequestScoped
public class AddRentalController implements Serializable {

    @Inject
    private RentalService rentalService;
    @Inject
    private AccountService accountService;
    private MovieRental movieRental;
    private BookRental bookRental;
    private Date todayDate;
    private String userUUID;

    @PostConstruct
    private void init() {
        movieRental = new MovieRental();
        bookRental = new BookRental();
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, -1);
        todayDate = c.getTime();
    }

    public MovieRental getMovieRental() { return movieRental; }
    public BookRental getBookRental() { return bookRental; }
    public Date getTodayDate() {return todayDate; }

    public List<Date> getDisabledDays() { return rentalService.getDisabledDays();}

    public void setMovie(Movie movie) {
        this.movieRental.setMovie(movie);
//        this.movieRental.setRange(movie.getRentalDateRange());
        this.movieRental.setRentalStart(movie.getRentalStart());
        this.movieRental.setRentalEnd(movie.getRentalEnd());
        //this.movieRental.checkDateOrder();
        this.movieRental.getMovie().setRented(true);
    }

    public void setBook(Book book) {
        this.bookRental.setBook(book);
//        this.bookRental.setRange(book.getRentalDateRange());
        this.bookRental.setRentalStart(book.getRentalStart());
        this.bookRental.setRentalEnd(book.getRentalEnd());
        //this.bookRental.checkDateOrder();
        this.bookRental.getBook().setRented(true);
    }

    public void setMovieAccount(Account account) {
        this.movieRental.setAccount(account);
    }

    public void setBookAccount(Account account) {
        this.bookRental.setAccount(account);
    }

    public void addMovieRentConfirmed() {
        movieRental.setAccount(accountService.getAccountViaUUID(userUUID));
        rentalService.addMovieRental(movieRental);
        init();
    }

    public void addBookRentConfirmed() {
        bookRental.setAccount(accountService.getAccountViaUUID(userUUID));
        rentalService.addBookRental(bookRental);
        init();
    }

    public String getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }
}
