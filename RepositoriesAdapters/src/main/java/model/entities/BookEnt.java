package model.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
public class BookEnt {

    private String title;
    private String author;
    private int pages;
    private boolean rented;
    private String id;
    // this params are for rentals
    // todo czy pola sa koniecznie?
    private String rentalUserUUID;
    private Date rentalStart;
    private Date rentalEnd;

    public BookEnt(String title, String author, int pages, boolean rented) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.rented = rented;
    }
}
