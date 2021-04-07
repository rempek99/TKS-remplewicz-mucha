package model_ent.entities;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
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
}
