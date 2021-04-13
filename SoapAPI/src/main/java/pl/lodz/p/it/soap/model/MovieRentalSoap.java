package pl.lodz.p.it.soap.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "movieRentalSoap", propOrder = {
        "movie",
        "account",
        "id",
        "rentalStart",
        "rentalEnd"
})
public class MovieRentalSoap {
    private MovieSoap movie;
    private AccountSoap account;
    private String id;
    private List<Date> range = new ArrayList<>();
    private Date rentalStart;
    private Date rentalEnd;

    public MovieRentalSoap() {
        this.id = UUID.randomUUID().toString();
    }

    public MovieRentalSoap(MovieSoap movie, AccountSoap account) {
        this.movie = movie;
        this.account = account;
        rentalStart = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        MovieRentalSoap that = (MovieRentalSoap) o;

        if(!that.getRange().containsAll(this.getRange())) return false;

        return new EqualsBuilder()
                .append(getMovie(), that.getMovie())
                .append(getAccount(), that.getAccount())
                .append(getId(), that.getId())
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