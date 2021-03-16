package model_ent.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
public class MovieEnt {

    private String title;
    private String author;
    private double rating;
    private boolean rented;
    private String id;
    // this params are for rentals
    private String rentalUserUUID;
    private Date rentalStart;
    private Date rentalEnd;

    public MovieEnt(String title, String author, double rating, boolean rented) {
        this.title = title;
        this.author = author;
        this.rating = rating;
        this.rented = rented;
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
        MovieEnt movie = (MovieEnt) o;
        return Double.compare(movie.getRating(), getRating()) == 0 &&
                isRented() == movie.isRented() &&
                getTitle().equals(movie.getTitle()) &&
                getAuthor().equals(movie.getAuthor()) &&
                Objects.equals(getId(), movie.getId()) &&
                Objects.equals(getRentalUserUUID(), movie.getRentalUserUUID()) &&
                getRentalStart().equals(movie.getRentalStart()) &&
                getRentalEnd().equals(movie.getRentalEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getAuthor(), getRating(), isRented(), getId(), getRentalUserUUID(), getRentalStart(), getRentalEnd());
    }
}
