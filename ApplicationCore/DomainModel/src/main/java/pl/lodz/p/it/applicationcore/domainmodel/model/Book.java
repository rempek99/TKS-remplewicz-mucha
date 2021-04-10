package pl.lodz.p.it.applicationcore.domainmodel.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Book {
    private String title;
    private String author;
    private int pages;
    private boolean rented;
    private String id;

    public Book() {
        this.id = UUID.randomUUID().toString();
    }

    public Book(String title, String author, int pages, boolean rented) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.rented = rented;
        this.id = UUID.randomUUID().toString();
    }
}