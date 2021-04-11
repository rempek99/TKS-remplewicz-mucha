package pl.lodz.p.it.viewmodel.modelDTO;

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
public class MovieRentalDTO {
    private MovieDTO movie;
    private AccountDTO account;
    private String id;
    private List<Date> range = new ArrayList<>();
    private Date rentalStart;
    private Date rentalEnd;

    public MovieRentalDTO() {
        this.id = UUID.randomUUID().toString();
    }

    public MovieRentalDTO(MovieDTO movie, AccountDTO account) {
        this.movie = movie;
        this.account = account;
        rentalStart = new Date();
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

        MovieRentalDTO that = (MovieRentalDTO) o;

        if(!that.getRange().containsAll(this.getRange())) return false;

        return new EqualsBuilder()
                .append(getMovie(), that.getMovie())
                .append(getAccount(), that.getAccount())
                .append(getId(), that.getId())
                .append(getRange(), that.getRange())
                .append(getRentalStart(), that.getRentalStart())
                .append(getRentalEnd(), that.getRentalEnd())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getMovie())
                .append(getAccount())
                .append(getId())
                .append(getRange())
                .append(getRentalStart())
                .append(getRentalEnd())
                .toHashCode();
    }
}