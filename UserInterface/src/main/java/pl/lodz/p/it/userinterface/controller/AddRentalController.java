package pl.lodz.p.it.userinterface.controller;

import pl.lodz.p.it.viewports.account.GetAccountViaUUIDUsecase;
import pl.lodz.p.it.viewmodel.modelDTO.*;
import pl.lodz.p.it.viewports.rentals.AddBookRentalUsecase;
import pl.lodz.p.it.viewports.rentals.AddMovieRentalUsecase;
import pl.lodz.p.it.viewports.rentals.GetDisabledDaysUsecase;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
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
    private AddBookRentalUsecase<BookRentalDTO> bookRentalService;
    @Inject
    private AddMovieRentalUsecase<MovieRentalDTO> movieRentalService;
    @Inject
    private GetDisabledDaysUsecase disabledDaysService;
    @Inject
    private GetAccountViaUUIDUsecase<AccountDTO> accountService;
    private MovieRentalDTO movieRentalDTO;
    private BookRentalDTO bookRentalDTO;
    private Date todayDate;
    private String userUUID;

    @PostConstruct
    private void init() {
        movieRentalDTO = new MovieRentalDTO();
        bookRentalDTO = new BookRentalDTO();
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, -1);
        todayDate = c.getTime();
    }

    public MovieRentalDTO getMovieRental() { return movieRentalDTO; }
    public BookRentalDTO getBookRental() { return bookRentalDTO; }
    public Date getTodayDate() {return todayDate; }

    public List<Date> getDisabledDays() { return disabledDaysService.getDisabledDays();}

    public void setMovie(MovieDTO movieDTO) {
        this.movieRentalDTO.setMovie(movieDTO);
//        this.movieRental.setRange(pl.lodz.p.it.viewports.movie.getRentalDateRange());
        this.movieRentalDTO.setRentalStart(movieRentalDTO.getRentalStart());
        this.movieRentalDTO.setRentalEnd(movieRentalDTO.getRentalEnd());
        //this.movieRental.checkDateOrder();
        this.movieRentalDTO.getMovie().setRented(true);
    }

    public void setBook(BookDTO bookDTO) {
        this.bookRentalDTO.setBook(bookDTO);
//        this.bookRental.setRange(pl.lodz.p.it.viewports.book.getRentalDateRange());
        this.bookRentalDTO.setRentalStart(bookRentalDTO.getRentalStart());
        this.bookRentalDTO.setRentalEnd(bookRentalDTO.getRentalEnd());
        //this.bookRental.checkDateOrder();
        this.bookRentalDTO.getBook().setRented(true);
    }

    public void setMovieAccount(AccountDTO account) {
        this.movieRentalDTO.setAccount(account);
    }

    public void setBookAccount(AccountDTO account) {
        this.bookRentalDTO.setAccount(account);
    }

    public void addMovieRentConfirmed() {
        movieRentalDTO.setAccount(accountService.getAccountViaUUID(userUUID));
        movieRentalService.addMovieRental(movieRentalDTO);
        init();
    }

    public void addBookRentConfirmed() {
        bookRentalDTO.setAccount(accountService.getAccountViaUUID(userUUID));
        bookRentalService.addBookRental(bookRentalDTO);
        init();
    }

    public String getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }
}
