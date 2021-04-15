package pl.lodz.p.it.repositoriesadapters.model_ent.entities;

import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        BookRentalEnt that = (BookRentalEnt) o;

        if(!that.getRange().containsAll(this.getRange())) return false;

        return new EqualsBuilder()
                .append(getBookEnt(), that.getBookEnt())
                .append(getAccountEnt(), that.getAccountEnt())
                .append(getId(), that.getId())
              //  .append(getRentalStart(), that.getRentalStart())
              //  .append(getRentalEnd(), that.getRentalEnd())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getBookEnt())
                .append(getAccountEnt())
                .append(getId())
               // .append(getRange())
               // .append(getRentalStart())
              //  .append(getRentalEnd())
                .toHashCode();
    }
}
