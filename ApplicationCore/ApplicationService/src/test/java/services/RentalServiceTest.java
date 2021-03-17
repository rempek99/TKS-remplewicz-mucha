package services;

import aggregates.adapters.RentalRepoAdapter;
import model.*;
import model_ent.repositories.RentalEntRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RentalServiceTest {

    private RentalService rentalService;

    private final Movie testMovie = new Movie("Test", "Tester", 100, false);
    private final Movie testMovie2 = new Movie("Test2", "Tester2", 200, true);
    private final Movie testMovie3 = new Movie("Test3", "Tester3", 300, true);

    private final Book testBook = new Book("Test", "Tester", 100, false);
    private final Book testBook2 = new Book("Test2", "Tester2", 200, true);
    private final Book testBook3 = new Book("Test3", "Tester3", 300, true);

    private final Account tester = new Account("Tester", "Testowy", "user", true, "test", "test123");
    private final Account tester2 = new Account("Tester2", "Testowy2", "user", true, "test2", "test1234");
    private final Account tester3 = new Account("Tester3", "Testowy3", "user", true, "test3", "test12345");

    @BeforeEach
    void initRentalService() {
        rentalService = new RentalService(new RentalRepoAdapter(new RentalEntRepo()));
        rentalService.addBookRental(new BookRental(testBook, tester));
        rentalService.addMovieRental(new MovieRental(testMovie, tester2));
    }

    @Test
    void getAllMovieRentals() {
        List<MovieRental> allMovieRentals = rentalService.getAllMovieRentals();
        assertFalse(allMovieRentals.isEmpty());
    }

    @Test
    void getAllBookRentals() {
        List<BookRental> allBookRentals = rentalService.getAllBookRentals();
        assertFalse(allBookRentals.isEmpty());
    }

    @Test
    void addMovieRental() {
        int size = rentalService.getAllMovieRentals().size();
        rentalService.addMovieRental(new MovieRental(testMovie2, tester3));
        assertEquals(size+1, rentalService.getAllMovieRentals().size());
    }

    @Test
    void addBookRental() {
        int size = rentalService.getAllBookRentals().size();
        rentalService.addBookRental(new BookRental(testBook2, tester3));
        assertEquals(size+1, rentalService.getAllBookRentals().size());
    }

    @Test
    void removeMovieRental() {
        int size = rentalService.getAllMovieRentals().size();
        MovieRental exampleMovieRental = rentalService.getAllMovieRentals().get(0);
        rentalService.removeMovieRental(exampleMovieRental);
        assertEquals(size-1, rentalService.getAllMovieRentals().size());
    }

    @Test
    void removeBookRental() {
        int size = rentalService.getAllBookRentals().size();
        BookRental exampleBookRental = rentalService.getAllBookRentals().get(0);
        rentalService.removeBookRental(exampleBookRental);
        assertEquals(size-1, rentalService.getAllBookRentals().size());
    }

    @Test
    void getMovieRentalViaUUID() {
        MovieRental exampleMovieRental = rentalService.getAllMovieRentals().get(0);
        MovieRental foundMovieRentalViaUUID = rentalService.getMovieRentalViaUUID(exampleMovieRental.getId());
        assertEquals(exampleMovieRental, foundMovieRentalViaUUID);
    }

    @Test
    void getBookRentalViaUUID() {
        BookRental exampleBookRental = rentalService.getAllBookRentals().get(0);
        BookRental foundBookRentalViaUUID = rentalService.getBookRentalViaUUID(exampleBookRental.getId());
        assertEquals(exampleBookRental, foundBookRentalViaUUID);
    }

    @Test
    void updateSingleBookRental() {
        MovieRental exampleMovieRental = rentalService.getAllMovieRentals().get(0);
        MovieRental newMovieRental = new MovieRental(testMovie2, tester);
        rentalService.updateSingleMovieRental(exampleMovieRental, newMovieRental);
        MovieRental foundMovieRental = rentalService.getMovieRentalViaUUID(exampleMovieRental.getId());
        assertEquals(newMovieRental.getMovie(), foundMovieRental.getMovie());
    }

    @Test
    void updateSingleMovieRental() {
        BookRental exampleBookRental = rentalService.getAllBookRentals().get(0);
        BookRental newBookRental = new BookRental(testBook2, tester3);
        rentalService.updateSingleBookRental(exampleBookRental, newBookRental);
        BookRental foundBookRental = rentalService.getBookRentalViaUUID(exampleBookRental.getId());
        assertEquals(newBookRental.getBook(), foundBookRental.getBook());
    }

    @Test
    void getDisabledDays() {
        assertNotNull(rentalService.getDisabledDays());
    }
}