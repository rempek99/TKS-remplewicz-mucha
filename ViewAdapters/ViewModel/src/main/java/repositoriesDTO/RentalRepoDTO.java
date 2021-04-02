package repositoriesDTO;

import modelDTO.BookRentalDTO;
import modelDTO.MovieRentalDTO;

import javax.enterprise.context.ApplicationScoped;
import java.util.*;

@ApplicationScoped
public class RentalRepoDTO {
    private List<MovieRentalDTO> movieRentals = Collections.synchronizedList(new ArrayList<MovieRentalDTO>());
    private List<BookRentalDTO> bookRentals = Collections.synchronizedList(new ArrayList<BookRentalDTO>());
    private List<Date> invalidDates = new ArrayList<Date>();

    public List<MovieRentalDTO> getMovieRentals() {
        return Collections.unmodifiableList(movieRentals);
    }
    public List<BookRentalDTO> getBookRentals() {
        return Collections.unmodifiableList(bookRentals);
    }

    public void removeMovieRental(MovieRentalDTO r) {
            movieRentals.remove(r);
    }
    public void removeBookRental(BookRentalDTO r) {
            bookRentals.remove(r);
    }

    public MovieRentalDTO addMovieRental(MovieRentalDTO r) {
            movieRentals.add(r);
            r.setId(UUID.randomUUID().toString());
//            printState();
        return r;
    }

    public BookRentalDTO addBookRental(BookRentalDTO r) {
            bookRentals.add(r);
            r.setId(UUID.randomUUID().toString());
//            printState();
        return r;
    }

    public MovieRentalDTO getMovieRentalViaUUID(String str) {
        for(MovieRentalDTO movie: movieRentals) {
            if(movie.getId().equals(str)){
                return movie;
            }
        }
        return null;
    }

    public BookRentalDTO getBookRentalViaUUID(String str) {
        for(BookRentalDTO book: bookRentals) {
            if(book.getId().equals(str)){
                return book;
            }
        }
        return null;
    }

    public MovieRentalDTO getMovieRental(MovieRentalDTO m) {
        if (movieRentals.contains(m)) {
            return m;
        } else {
            return null;
        }
    }

    public BookRentalDTO getBookRental(BookRentalDTO b) {
        if (bookRentals.contains(b)) {
            return b;
        } else {
            return null;
        }
    }

    public MovieRentalDTO updateSingleMovieRental(MovieRentalDTO movieRentalToChange, MovieRentalDTO movieRentalWithData) {
        MovieRentalDTO fromRepo = getMovieRental(movieRentalToChange);
        fromRepo.setMovieDTO(movieRentalWithData.getMovieDTO());
        fromRepo.setAccountDTO(movieRentalWithData.getAccountDTO());
        fromRepo.setRange(movieRentalWithData.getRange());
        fromRepo.setRentalStart(movieRentalWithData.getRentalStart());
        fromRepo.setRentalEnd(movieRentalWithData.getRentalEnd());
        return fromRepo;
    }

    public BookRentalDTO updateSingleBookRental(BookRentalDTO bookRentalToChange, BookRentalDTO bookRentalWithData) {
        BookRentalDTO fromRepo = getBookRental(bookRentalToChange);
        fromRepo.setBookDTO(bookRentalWithData.getBookDTO());
        fromRepo.setAccountDTO(bookRentalWithData.getAccountDTO());
        fromRepo.setRange(bookRentalWithData.getRange());
        fromRepo.setRentalStart(bookRentalWithData.getRentalStart());
        fromRepo.setRentalEnd(bookRentalWithData.getRentalEnd());
        return fromRepo;
    }

//    private void printState() {
//        System.out.println(Arrays.toString(movieRentals.toArray()));
//        System.out.println(Arrays.toString(bookRentals.toArray()));
//    }

    public List<Date> getDisabledDays() {
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, -1);
        invalidDates.add(c.getTime());
        return invalidDates;
    }
}
