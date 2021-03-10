package model.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class MovieEnt {

    @NotNull(message = "Please enter title of movie")
    private String title;
    @NotNull(message = "Please enter author of movie")
    @Pattern(regexp = "[a-zA-Z]+")
    private String author;
    @NotNull(message = "Please enter correct score for movie")
    @Digits(integer = 2, fraction = 1)
    @DecimalMin("0.0")
    @DecimalMax("10.0")
    private double rating;
    @NotNull
    private boolean rented;
    private String id;
    // this params are for rentals
    private String rentalUserUUID;
    private Date rentalStart;
    private Date rentalEnd;

    @JsonCreator
    public MovieEnt() {
        this.id = UUID.randomUUID().toString();
        this.rentalUserUUID = "";
        this.rentalStart = new Date();
        this.rentalEnd = new Date();
    }

    @JsonCreator
    public MovieEnt(@JsonProperty("title") String title, @JsonProperty("author") String author, @JsonProperty("rating") double rating, @JsonProperty("rented") boolean rented) {
        this.title = title;
        this.author = author;
        this.rating = rating;
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

    public double getRating() { return rating; }

    public void setRating(double rating) { this.rating = rating; }

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
        return "Movie{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", rating=" + rating +
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
        if (!(o instanceof MovieEnt)) return false;
        MovieEnt movieEnt = (MovieEnt) o;
        return Double.compare(movieEnt.getRating(), getRating()) == 0 &&
                isRented() == movieEnt.isRented() &&
                getTitle().equals(movieEnt.getTitle()) &&
                getAuthor().equals(movieEnt.getAuthor()) &&
                Objects.equals(getId(), movieEnt.getId()) &&
                Objects.equals(getRentalUserUUID(), movieEnt.getRentalUserUUID()) &&
                getRentalStart().equals(movieEnt.getRentalStart()) &&
                getRentalEnd().equals(movieEnt.getRentalEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getAuthor(), getRating(), isRented(), getId(), getRentalUserUUID(), getRentalStart(), getRentalEnd());
    }
}
