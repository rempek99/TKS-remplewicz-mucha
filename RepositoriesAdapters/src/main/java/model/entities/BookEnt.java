package model.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class BookEnt {

    @NotNull(message = "Please enter title of book")
    private String title;
    @NotNull(message = "Please enter author of book")
    @Pattern(regexp = "[a-zA-Z]+")
    private String author;
    @NotNull
    private int pages;
    @NotNull
    private boolean rented;
    private String id;
    // this params are for rentals
    private String rentalUserUUID;
    private Date rentalStart;
    private Date rentalEnd;

    @JsonCreator
    public BookEnt() {
        this.id = UUID.randomUUID().toString();
        this.rentalUserUUID = "";
        this.rentalStart = new Date();
        this.rentalEnd = new Date();
    }

    @JsonCreator
    public BookEnt(@JsonProperty("title") String title, @JsonProperty("author") String author, @JsonProperty("pages") int pages, @JsonProperty("rented") boolean rented) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.rented = rented;
        this.id = UUID.randomUUID().toString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public int getPages() { return pages; }

    public void setPages(int pages) { this.pages = pages; }

    public boolean getRented() { return rented; }

    public void setRented(boolean rented) { this.rented = rented; }

    public String getId() {
        return id;
    }

    public void setId(String id) {this.id = id; }

    public boolean isRented() {
        return rented;
    }

    public String getRentalUserUUID() {
        return rentalUserUUID;
    }

    public void setRentalUserUUID(String rentalUserUUID) {
        this.rentalUserUUID = rentalUserUUID;
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

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", pages=" + pages +
                ", rented=" + rented +
                ", id='" + id + '\'' +
                ", rentalUserUUID='" + rentalUserUUID + '\'' +
                ", rentalStart=" + rentalStart +
                ", rentalEnd=" + rentalEnd +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookEnt)) return false;
        BookEnt bookEnt = (BookEnt) o;
        return getPages() == bookEnt.getPages() &&
                isRented() == bookEnt.isRented() &&
                getTitle().equals(bookEnt.getTitle()) &&
                getAuthor().equals(bookEnt.getAuthor()) &&
                Objects.equals(getId(), bookEnt.getId()) &&
                Objects.equals(getRentalUserUUID(), bookEnt.getRentalUserUUID()) &&
                getRentalStart().equals(bookEnt.getRentalStart()) &&
                getRentalEnd().equals(bookEnt.getRentalEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getAuthor(), getPages(), isRented(), getId(), getRentalUserUUID(), getRentalStart(), getRentalEnd());
    }
}
