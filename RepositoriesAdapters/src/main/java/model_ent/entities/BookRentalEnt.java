package model_ent.entities;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
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

    public void checkDateOrder() {
        if(getRentalStart().after(getRentalEnd())){
            Date tmp = getRentalStart();
            setRentalStart(getRentalEnd());
            setRentalEnd(tmp);
        }
    }
}
