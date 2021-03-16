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

    public BookEnt getBookEnt() {
        return bookEnt;
    }

    public void setBookEnt(BookEnt book) {
        this.bookEnt = book;
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
        return "BookRental{" +
                "book=" + bookEnt +
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
        if (!(o instanceof BookRentalEnt)) return false;
        BookRentalEnt that = (BookRentalEnt) o;
        return getBookEnt().equals(that.getBookEnt()) &&
                getAccountEnt().equals(that.getAccountEnt()) &&
                getId().equals(that.getId()) &&
                Objects.equals(getRange(), that.getRange()) &&
                getRentalStart().equals(that.getRentalStart()) &&
                getRentalEnd().equals(that.getRentalEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBookEnt(), getAccountEnt(), getId(), getRange(), getRentalStart(), getRentalEnd());
    }
}
