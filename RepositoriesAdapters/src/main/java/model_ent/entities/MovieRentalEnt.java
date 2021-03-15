package model_ent.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
}
