package model.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.*;

public class BookRentalEnt {
    @NotNull
    private BookEnt bookEnt;
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

    public BookRentalEnt() {
        this.id = UUID.randomUUID().toString();
    }

    @JsonCreator
    public BookRentalEnt(@JsonProperty("book") BookEnt bookEnt, @JsonProperty("account") AccountEnt accountEnt) {
        this.bookEnt = bookEnt;
        this.accountEnt = accountEnt;
        this.id = UUID.randomUUID().toString();
    }

    public BookEnt getBook() {
        return bookEnt;
    }

    public void setBook(BookEnt bookEnt) {
        this.bookEnt = bookEnt;
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
        return getBook().equals(that.getBook()) &&
                getAccount().equals(that.getAccount()) &&
                getId().equals(that.getId()) &&
                Objects.equals(getRange(), that.getRange()) &&
                getRentalStart().equals(that.getRentalStart()) &&
                getRentalEnd().equals(that.getRentalEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBook(), getAccount(), getId(), getRange(), getRentalStart(), getRentalEnd());
    }
}
