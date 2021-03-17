package model_ent.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
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

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", pages=" + pages +
                ", rented=" + rented +
                ", id='" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookEnt)) return false;
        BookEnt book = (BookEnt) o;
        return getPages() == book.getPages() &&
                isRented() == book.isRented() &&
                getTitle().equals(book.getTitle()) &&
                getAuthor().equals(book.getAuthor()) &&
                Objects.equals(getId(), book.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getAuthor(), getPages(), isRented(), getId());
    }
}
