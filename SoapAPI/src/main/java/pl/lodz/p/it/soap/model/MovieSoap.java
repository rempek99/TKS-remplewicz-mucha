package pl.lodz.p.it.soap.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "movieSoap", propOrder = {
        "title",
        "author",
        "rating",
        "rented",
        "id"
})
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