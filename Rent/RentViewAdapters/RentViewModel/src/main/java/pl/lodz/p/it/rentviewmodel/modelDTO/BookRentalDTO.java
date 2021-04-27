package pl.lodz.p.it.rentviewmodel.modelDTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.*;

@Getter
@Setter
@ToString
public class BookRentalDTO {
    private BookDTO book;
    private ClientDTO account;
    private String id;
    private List<Date> range = new ArrayList<>();
    private Date rentalStart;
    private Date rentalEnd;

    public BookRentalDTO() {
        this.id = UUID.randomUUID().toString();
    }

    public BookRentalDTO(BookDTO book, ClientDTO account) {
        this.book = book;
        this.account = account;
        this.id = UUID.randomUUID().toString();
        rentalStart = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        BookRentalDTO that = (BookRentalDTO) o;

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
                .append(getId())
               // .append(getRange())
                //.append(getRentalStart())
               // .append(getRentalEnd())
                .toHashCode();
    }
}
