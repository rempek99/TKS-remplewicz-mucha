package pl.lodz.p.it.rentrepositoriesadapters.model_ent.entities;

import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class BookEnt {

    private String title;
    private String author;
    private int pages;
    private boolean rented;
    private String id;

    public BookEnt(String title, String author, int pages, boolean rented) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.rented = rented;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        BookEnt bookEnt = (BookEnt) o;

        return new EqualsBuilder()
                .append(getPages(), bookEnt.getPages())
                .append(isRented(), bookEnt.isRented())
                .append(getTitle(), bookEnt.getTitle())
                .append(getAuthor(), bookEnt.getAuthor())
                .append(getId(), bookEnt.getId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getTitle())
                .append(getAuthor())
                .append(getPages())
                .append(isRented())
                .append(getId())
                .toHashCode();
    }
}
