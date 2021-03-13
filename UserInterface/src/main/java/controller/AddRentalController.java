package controller;

import dtoadapters.*;
import dtomodel.*;
import model.*;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Named
@RequestScoped
public class AddRentalController implements Serializable {
    private RentalDTOAdapter rentalDTOAdapter;

    private MovieRentalDTO movieRental;
    private BookRentalDTO bookRental;
    private Date todayDate;

    @PostConstruct
    private void init() {
        movieRental = new MovieRentalDTO();
        bookRental = new BookRentalDTO();
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, -1);
        todayDate = c.getTime();
    }

    public MovieRentalDTO getMovieRental() { return rentalDTOAdapter.getMovieRental(movieRental); }
    public BookRentalDTO getBookRental() { return rentalDTOAdapter.getBookRental(bookRental); }
    public Date getTodayDate() {return todayDate; }

    public List<Date> getDisabledDays() { return rentalDTOAdapter.getDisabledDays();}

    public void setMovie(MovieDTO movie) {
        this.movieRental.setMovie(movie);
//        this.movieRental.setRange(movie.getRentalDateRange());
        this.movieRental.setRentalStart(movie.getRentalStart());
        this.movieRental.setRentalEnd(movie.getRentalEnd());
        this.movieRental.checkDateOrder();
        this.movieRental.getMovie().setRented(true);
    }

    public void setBook(BookDTO book) {
        this.bookRental.setBook(book);
//        this.bookRental.setRange(book.getRentalDateRange());
        this.bookRental.setRentalStart(book.getRentalStart());
        this.bookRental.setRentalEnd(book.getRentalEnd());
        this.bookRental.checkDateOrder();
        this.bookRental.getBook().setRented(true);
    }

    public void setMovieAccount(AccountDTO account) {
        this.movieRental.setAccount(account);
    }

    public void setBookAccount(AccountDTO account) {
        this.bookRental.setAccount(account);
    }

    public void addMovieRentConfirmed() {
        rentalDTOAdapter.addMovieRental(movieRental);
        init();
    }

    public void addBookRentConfirmed() {
        rentalDTOAdapter.addBookRental(bookRental);
        init();
    }
}
