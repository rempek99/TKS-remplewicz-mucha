package model_ent.entities;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
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

}
