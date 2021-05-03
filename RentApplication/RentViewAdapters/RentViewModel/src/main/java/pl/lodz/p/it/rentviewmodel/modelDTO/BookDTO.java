package pl.lodz.p.it.rentviewmodel.modelDTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BookDTO {
    private String title;
    private String author;
    private int pages;
    private boolean rented;
    private String id;

    public BookDTO() {
        this.id = UUID.randomUUID().toString();
    }

    public BookDTO(String title, String author, int pages, boolean rented) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.rented = rented;
        this.id = UUID.randomUUID().toString();
    }
}