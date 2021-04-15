package pl.lodz.p.it.applicationports.infrastructure;

import pl.lodz.p.it.applicationcore.domainmodel.model.BookRental;
import pl.lodz.p.it.applicationcore.domainmodel.model.MovieRental;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RentalPort {
    List<MovieRental> getMovieRentals();
    List<BookRental> getBookRentals();
    MovieRental addMovieRental(MovieRental r);
    BookRental addBookRental(BookRental r);
    void removeMovieRental(MovieRental r);
    void removeBookRental(BookRental r);
    MovieRental getMovieRental(MovieRental m);
    BookRental getBookRental(BookRental b);
    Optional<MovieRental> getMovieRentalViaUUID(String str);
    Optional<BookRental> getBookRentalViaUUID(String str);
    void updateSingleBookRental(BookRental income, BookRental outcome);
    void updateSingleMovieRental(MovieRental income, MovieRental outcome);
    List<Date> getDisabledDays();
}
