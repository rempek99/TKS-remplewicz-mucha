package modelDTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BookRentalDTO {
    private BookDTO book;
    private AccountDTO account;
    private String id;
    private List<Date> range = new ArrayList<>();
    private Date rentalStart;
    private Date rentalEnd;

    public BookRentalDTO() {
        this.id = UUID.randomUUID().toString();
    }

    public BookRentalDTO(BookDTO book, AccountDTO account) {
        this.book = book;
        this.account = account;
        this.id = UUID.randomUUID().toString();
        rentalStart = new Date();
    }

    public BookDTO getBookDTO() {
        return book;
    }

    public void setBookDTO(BookDTO book) {
        this.book = book;
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
        if (!(o instanceof BookRentalDTO)) return false;
        BookRentalDTO that = (BookRentalDTO) o;
        if(rentalEnd == null) {
            if(that.getRentalEnd()!=null)
                return false;
        }
        else{
            if(that.getRentalEnd() == null || that.getRentalEnd()!=rentalEnd)
                return false;
        }
        return getBookDTO().equals(that.getBookDTO()) &&
                getAccountDTO().equals(that.getAccountDTO()) &&
                getId().equals(that.getId()) &&
                Objects.equals(getRange(), that.getRange()) &&
                getRentalStart().equals(that.getRentalStart());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBookDTO(), getAccountDTO(), getId(), getRange(), getRentalStart(), getRentalEnd());
    }
}
