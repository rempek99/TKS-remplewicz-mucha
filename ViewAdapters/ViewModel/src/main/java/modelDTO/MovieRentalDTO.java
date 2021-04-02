package modelDTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.AccountDTO;

import java.util.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MovieRentalDTO {
    private MovieDTO movie;
    private AccountDTO account;
    private String id;
    private List<Date> range = new ArrayList<>();
    private Date rentalStart;
    private Date rentalEnd;

    public MovieRentalDTO() {
        this.id = UUID.randomUUID().toString();
    }

    public MovieRentalDTO(MovieDTO movie, AccountDTO account) {
        this.movie = movie;
        this.account = account;
        rentalStart = new Date();
    }

    public MovieDTO getMovieDTO() {
        return movie;
    }

    public void setMovieDTO(MovieDTO movie) {
        this.movie = movie;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AccountDTO getAccountDTO() {
        return account;
    }

    public void setAccountDTO(AccountDTO account) {
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
        if (!(o instanceof MovieRentalDTO)) return false;
        MovieRentalDTO that = (MovieRentalDTO) o;
        if(rentalEnd == null) {
            if(that.getRentalEnd()!=null)
                return false;
        }
        else{
            if(that.getRentalEnd() == null || that.getRentalEnd()!=rentalEnd)
                return false;
        }
        return getMovieDTO().equals(that.getMovieDTO()) &&
                getAccountDTO().equals(that.getAccountDTO()) &&
                getId().equals(that.getId()) &&
                Objects.equals(getRange(), that.getRange()) &&
                getRentalStart().equals(that.getRentalStart());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMovieDTO(), getAccountDTO(), getId(), getRange(), getRentalStart(), getRentalEnd());
    }
}