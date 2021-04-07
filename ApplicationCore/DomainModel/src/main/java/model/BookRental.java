package model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BookRental {
    private Book book;
    private Account account;
    private String id;
    private List<Date> range = new ArrayList<>();
    private Date rentalStart;
    private Date rentalEnd;

    public BookRental() {
        this.id = UUID.randomUUID().toString();
    }

    public BookRental(Book book, Account account) {
        this.book = book;
        this.account = account;
        this.id = UUID.randomUUID().toString();
        rentalStart = new Date();
    }

    public void checkDateOrder() {
        if(getRentalStart().after(getRentalEnd())){
            Date tmp = getRentalStart();
            setRentalStart(getRentalEnd());
            setRentalEnd(tmp);
        }
    }
}
