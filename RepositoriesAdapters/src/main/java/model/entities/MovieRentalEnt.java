package model.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@XmlRootElement
public class MovieRentalEnt {
    @NotNull
    private MovieEnt movieEnt;
    @NotNull
    private AccountEnt accountEnt;
    @NotNull
    private String id;
    @NotNull
    private List<Date> range = new ArrayList<>();
    @NotNull
    private Date rentalStart;
    @NotNull
    private Date rentalEnd;

    public MovieRentalEnt() {
        this.id = UUID.randomUUID().toString();
    }

    @JsonCreator
    public MovieRentalEnt(@JsonProperty("movie") MovieEnt movieEnt, @JsonProperty("account") AccountEnt accountEnt) {
        this.movieEnt = movieEnt;
        this.accountEnt = accountEnt;
        this.id = UUID.randomUUID().toString();
    }

    public MovieEnt getMovie() {
        return movieEnt;
    }

    public void setMovie(MovieEnt movieEnt) {
        this.movieEnt = movieEnt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AccountEnt getAccount() {
        return accountEnt;
    }

    public void setAccount(AccountEnt accountEnt) {
        this.accountEnt = accountEnt;
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
