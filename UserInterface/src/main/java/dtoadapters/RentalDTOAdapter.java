package dtoadapters;

import controller.RentalService;
import dtoconverters.*;
import dtomodel.*;
import model.BookRental;
import model.MovieRental;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static dtoconverters.BookRentalDTOConverter.convertBookRentalToDTO;
import static dtoconverters.BookRentalDTOConverter.convertDTOToBookRental;
import static dtoconverters.MovieRentalDTOConverter.convertDTOToMovieRental;
import static dtoconverters.MovieRentalDTOConverter.convertMovieRentalToDTO;

public class RentalDTOAdapter {
    final RentalService rentalService;
    final MovieRentalDTOConverter movieRentalDTOConverter;
    final BookRentalDTOConverter bookRentalDTOConverter;

    public RentalDTOAdapter(RentalService rentalService, MovieRentalDTOConverter movieRentalDTOConverter, BookRentalDTOConverter bookRentalDTOConverter) {
        this.rentalService = rentalService;
        this.movieRentalDTOConverter = movieRentalDTOConverter;
        this.bookRentalDTOConverter = bookRentalDTOConverter;
    }

    public List<MovieRentalDTO> getMovieRentals() {
        List<MovieRentalDTO> temp = Collections.synchronizedList(new ArrayList<MovieRentalDTO>());
        List<MovieRental> temp2 = rentalService.getAllMovieRentals();
        for (MovieRental movieRental: temp2) {
            temp.add(convertMovieRentalToDTO(movieRental));
        }
        return temp;
    }

    public List<BookRentalDTO> getBookRentals() {
        List<BookRentalDTO> temp = Collections.synchronizedList(new ArrayList<BookRentalDTO>());
        List<BookRental> temp2 = rentalService.getAllBookRentals();
        for (BookRental bookRental: temp2) {
            temp.add(convertBookRentalToDTO(bookRental));
        }
        return temp;
    }

    public void removeMovieRental(MovieRentalDTO r) { rentalService.removeMovieRental(convertDTOToMovieRental(r));}

    public void removeBookRental(BookRentalDTO r){
        rentalService.removeBookRental(convertDTOToBookRental(r));
    }

    public void addMovieRental(MovieRentalDTO r) {
        rentalService.addMovieRental(convertDTOToMovieRental(r));
    }

    public void addBookRental(BookRentalDTO r) {
        rentalService.addBookRental(convertDTOToBookRental(r));
    }

    public MovieRentalDTO getMovieRentalViaUUID(String str) {
        List<MovieRentalDTO> temp = getMovieRentals();
        for(MovieRentalDTO movie: temp) {
            if(movie.getId().equals(str)){
                return movie;
            }
        }
        return null;
    }

    public BookRentalDTO getBookRentalViaUUID(String str) {
        List<BookRentalDTO> temp = getBookRentals();
        for(BookRentalDTO book: temp) {
            if(book.getId().equals(str)){
                return book;
            }
        }
        return null;
    }

    public MovieRentalDTO getMovieRental(MovieRentalDTO m) {
        List<MovieRentalDTO> temp = getMovieRentals();
        if (temp.contains(m)) {
            return m;
        } else {
            return null;
        }
    }

    public BookRentalDTO getBookRental(BookRentalDTO b) {
        List<BookRentalDTO> temp = getBookRentals();
        if (temp.contains(b)) {
            return b;
        } else {
            return null;
        }
    }

    public void updateSingleMovieRental(MovieRentalDTO  movieRentalToChange, MovieRentalDTO  movieRentalWithData) {
        MovieRentalDTO  fromRepo = getMovieRental(movieRentalToChange);
        fromRepo.setId(movieRentalWithData.getId());
        fromRepo.setMovie(movieRentalWithData.getMovie());
        fromRepo.setAccount(movieRentalWithData.getAccount());
        fromRepo.setRange(movieRentalWithData.getRange());
        fromRepo.setRentalStart(movieRentalWithData.getRentalStart());
        fromRepo.setRentalEnd(movieRentalWithData.getRentalEnd());
    }

    public void updateSingleBookRental(BookRentalDTO  bookRentalToChange, BookRentalDTO  bookRentalWithData) {
        BookRentalDTO  fromRepo = getBookRental(bookRentalToChange);
        fromRepo.setId(bookRentalWithData.getId());
        fromRepo.setBook(bookRentalWithData.getBook());
        fromRepo.setAccount(bookRentalWithData.getAccount());
        fromRepo.setRange(bookRentalWithData.getRange());
        fromRepo.setRentalStart(bookRentalWithData.getRentalStart());
        fromRepo.setRentalEnd(bookRentalWithData.getRentalEnd());
    }


    public List<Date> getDisabledDays() {
        return rentalService.getDisabledDays();
    }
}
