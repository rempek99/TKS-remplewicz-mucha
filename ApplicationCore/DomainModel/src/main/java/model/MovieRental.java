package model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
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
    public void checkDateOrder() {
        if(getRentalStart().after(getRentalEnd())){
            Date tmp = getRentalStart();
            setRentalStart(getRentalEnd());
            setRentalEnd(tmp);
        }
    }
}