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
public class MovieRentalEnt {
    private MovieEnt movieEnt;
    private AccountEnt accountEnt;
    private String id;
    private List<Date> range = new ArrayList<>();
    private Date rentalStart;
    private Date rentalEnd;

    public MovieRentalEnt(MovieEnt movieEnt, AccountEnt accountEnt) {
        this.movieEnt = movieEnt;
        this.accountEnt = accountEnt;
        range = new ArrayList<>();
        rentalStart = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        MovieRentalEnt that = (MovieRentalEnt) o;

        if(!that.getRange().containsAll(this.getRange())) return false;

        return new EqualsBuilder()
                .append(getMovieEnt(), that.getMovieEnt())
                .append(getAccountEnt(), that.getAccountEnt())
                .append(getId(), that.getId())
                .append(getRentalStart(), that.getRentalStart())
                .append(getRentalEnd(), that.getRentalEnd())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getMovieEnt())
                .append(getAccountEnt())
                .append(getId())
                .append(getRange())
                .append(getRentalStart())
                .append(getRentalEnd())
                .toHashCode();
    }
}
