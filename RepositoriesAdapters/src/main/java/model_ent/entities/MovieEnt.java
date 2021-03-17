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

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", rating=" + rating +
                ", rented=" + rented +
                ", id='" + id +
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
                Objects.equals(getId(), movie.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getAuthor(), getRating(), isRented(), getId());
    }
}
