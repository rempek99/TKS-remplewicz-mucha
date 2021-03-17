package model_ent.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
public class MovieRentalEnt {
    private MovieEnt movieEnt;
    private AccountEnt accountEnt;
    private String id;
    private List<Date> range = new ArrayList<>();
    private Date rentalStart;
    private Date rentalEnd;

    public MovieRentalEnt(MovieEnt movieEnt, AccountEnt accountEnt) {
        this.movieEnt = movieEnt;
        this.accountEnt = accountEnt;
        range = new ArrayList<>();
        rentalStart = new Date();
    }

    public MovieEnt getMovieEnt() {
        return movieEnt;
    }

    public void setMovieEnt(MovieEnt movie) {
        this.movieEnt = movie;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AccountEnt getAccountEnt() {
        return accountEnt;
    }

    public void setAccountEnt(AccountEnt account) {
        this.accountEnt = account;
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
                "movie=" + movieEnt +
                ", account=" + accountEnt +
                ", id='" + id + '\'' +
                ", range=" + range +
                ", rentalStart=" + rentalStart +
                ", rentalEnd=" + rentalEnd +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieRentalEnt)) return false;
        MovieRentalEnt that = (MovieRentalEnt) o;
        if(rentalEnd == null) {
            if(that.getRentalEnd()!=null)
                return false;
        }
        else{
            if(that.getRentalEnd() == null || that.getRentalEnd()!=rentalEnd)
                return false;
        }
        return getMovieEnt().equals(that.getMovieEnt()) &&
                getAccountEnt().equals(that.getAccountEnt()) &&
                getId().equals(that.getId()) &&
                Objects.equals(getRange(), that.getRange()) &&
                getRentalStart().equals(that.getRentalStart());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMovieEnt(), getAccountEnt(), getId(), getRange(), getRentalStart(), getRentalEnd());
    }
}
