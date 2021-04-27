package pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.*;

@Getter
@Setter
@ToString
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
        if (getRentalStart().after(getRentalEnd())) {
            Date tmp = getRentalStart();
            setRentalStart(getRentalEnd());
            setRentalEnd(tmp);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        BookRental that = (BookRental) o;

        if(!that.getRange().containsAll(this.getRange())) return false;

        return new EqualsBuilder()
                .append(getBook(), that.getBook())
                .append(getAccount(), that.getAccount())
                .append(getId(), that.getId())
                //.append(getRentalStart(), that.getRentalStart())
                //.append(getRentalEnd(), that.getRentalEnd())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getBook())
                .append(getAccount())
                .append(getId()).append(getRange())
                //.append(getRentalStart())
                //.append(getRentalEnd())
                .toHashCode();
    }
}
