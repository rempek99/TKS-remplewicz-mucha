package pl.lodz.p.it.soap.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MovieSoap {
    private String title;
    private String author;
    private double rating;
    private boolean rented;
    private String id;

    public MovieSoap() {
        this.id = UUID.randomUUID().toString();
    }

    public MovieSoap(String title, String author, double rating, boolean rented) {
        this.title = title;
        this.author = author;
        this.rating = rating;
        this.rented = rented;
        this.id = UUID.randomUUID().toString();
    }
}