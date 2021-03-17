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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
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
        if(null == rentalEnd)
            return null;
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
                "book=" + book +
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
        if (!(o instanceof BookRental)) return false;
        BookRental that = (BookRental) o;
        if(rentalEnd == null) {
            if(that.getRentalEnd()!=null)
                return false;
        }
        else{
            if(that.getRentalEnd() == null || that.getRentalEnd()!=rentalEnd)
                return false;
        }
        return getBook().equals(that.getBook()) &&
                getAccount().equals(that.getAccount()) &&
                getId().equals(that.getId()) &&
                Objects.equals(getRange(), that.getRange()) &&
                getRentalStart().equals(that.getRentalStart());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBook(), getAccount(), getId(), getRange(), getRentalStart(), getRentalEnd());
    }
}
