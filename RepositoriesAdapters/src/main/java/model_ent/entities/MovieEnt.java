package model_ent.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

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
}
