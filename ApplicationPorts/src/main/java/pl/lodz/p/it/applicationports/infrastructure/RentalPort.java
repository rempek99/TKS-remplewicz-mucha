package pl.lodz.p.it.applicationports.infrastructure;

import pl.lodz.p.it.applicationcore.domainmodel.model.BookRental;
import pl.lodz.p.it.applicationcore.domainmodel.model.MovieRental;
import java.util.Date;
import java.util.List;

public interface RentalPort {
    List<MovieRental> getMovieRentals();
    List<BookRental> getBookRentals();
    void addMovieRental(MovieRental r);
    void addBookRental(BookRental r);
    void removeMovieRental(MovieRental r);
    void removeBookRental(BookRental r);
    MovieRental getMovieRental(MovieRental m);
    BookRental getBookRental(BookRental b);
    MovieRental getMovieRentalViaUUID(String str);
    BookRental getBookRentalViaUUID(String str);
    void updateSingleBookRental(BookRental income, BookRental outcome);
    void updateSingleMovieRental(MovieRental income, MovieRental outcome);
    List<Date> getDisabledDays();
}
