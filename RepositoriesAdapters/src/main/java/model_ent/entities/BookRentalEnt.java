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
public class BookRentalEnt {
    private BookEnt bookEnt;
    private AccountEnt accountEnt;
    private String id;
    private List<Date> range = new ArrayList<>();
    private Date rentalStart;
    private Date rentalEnd;

    public BookRentalEnt(BookEnt bookEnt, AccountEnt accountEnt) {
        this.bookEnt = bookEnt;
        this.accountEnt = accountEnt;
        rentalStart = new Date();
        range = new ArrayList<>();
    }
}
