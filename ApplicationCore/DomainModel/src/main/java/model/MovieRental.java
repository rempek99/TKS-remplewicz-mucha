package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

public class MovieRental {
    private Movie movie;
    private Account account;
    private String id;
    private List<Date> range = new ArrayList<>();
    private Date rentalStart;
    private Date rentalEnd;

    public  MovieRental() {
        this.id = UUID.randomUUID().toString();
    }

    public MovieRental(Movie movie, Account account) {
        this.movie = movie;
        this.account = account;
        this.id = UUID.randomUUID().toString();
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Date> getRange() {
        return range;
    }

    public void setRange(List<Date> range) {
        this.range = range;
    }

    public Date getRentalStart() {
        return rentalStart;
    }

    public void setRentalStart(Date rentalStart) {
        this.rentalStart = rentalStart;
    }

    public Date getRentalEnd() {
        return rentalEnd;
    }

    public void setRentalEnd(Date rentalEnd) {
        this.rentalEnd = rentalEnd;
    }

    public void checkDateOrder() {
        if(getRentalStart().after(getRentalEnd())){
            Date tmp = getRentalStart();
            setRentalStart(getRentalEnd());
            setRentalEnd(tmp);
        }
    }

    @Override
    public String toString() {
        return "MovieRental{" +
                "movie=" + movie +
                ", account=" + account +
                ", id='" + id + '\'' +
                ", range=" + range +
                ", rentalStart=" + rentalStart +
                ", rentalEnd=" + rentalEnd +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieRental)) return false;
        MovieRental that = (MovieRental) o;
        return getMovie().equals(that.getMovie()) &&
                getAccount().equals(that.getAccount()) &&
                getId().equals(that.getId()) &&
                Objects.equals(getRange(), that.getRange()) &&
                getRentalStart().equals(that.getRentalStart()) &&
                getRentalEnd().equals(that.getRentalEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMovie(), getAccount(), getId(), getRange(), getRentalStart(), getRentalEnd());
    }
}
