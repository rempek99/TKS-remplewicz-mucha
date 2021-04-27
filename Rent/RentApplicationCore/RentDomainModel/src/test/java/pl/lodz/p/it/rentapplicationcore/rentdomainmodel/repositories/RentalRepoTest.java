package pl.lodz.p.it.rentapplicationcore.rentdomainmodel.repositories;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.*;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RentalRepoTest {

    private static final int NUMBER_OF_BOOK_RENTALS = 2;
    private static final int NUMBER_OF_MOVIE_RENTALS = 1;


    private RentalRepo repo;

    private final Movie testMovie = new Movie("Test", "Tester", 100,false);
    private final Book testBook = new Book("Test", "Tester", 100,false);
    private final Client testAccount = new Client("Tester", "Testowy", "user", true, "test", "test123");
    private MovieRental my_movie;
    private BookRental my_book;

    private final BookRental tester = new BookRental();
    private final BookRental tester2 = new BookRental();
    private final MovieRental tester3 = new MovieRental();

    @BeforeAll
    void initTesters() {
        tester.setBook(testBook);
        tester.setAccount(testAccount);
        tester.setRentalStart(new Date());
        tester.setRange(new ArrayList<>());
        tester.setRentalEnd(new Date());

        tester2.setBook(testBook);
        tester2.setAccount(testAccount);
        tester2.setRentalStart(new Date());
        tester2.setRange(new ArrayList<>());
        tester2.setRentalEnd(new Date());

        tester3.setMovie(testMovie);
        tester3.setAccount(testAccount);
        tester3.setRentalStart(new Date());
        tester3.setRange(new ArrayList<>());
        tester3.setRentalEnd(new Date());
    }

    @BeforeEach
    void initRepo() {
        my_movie = new MovieRental();
        my_movie.setMovie(new Movie("trelo","morelo", 0.3,false));
        my_movie.setAccount(testAccount);
        my_movie.setRentalStart(new Date());
        my_movie.setRange(new ArrayList<>());
        my_movie.setRentalEnd(new Date());

        my_book = new BookRental();
        my_book.setBook(new Book("trelo","morelo", 103,false));
        my_book.setAccount(testAccount);
        my_book.setRentalStart(new Date());
        my_book.setRange(new ArrayList<>());
        my_book.setRentalEnd(new Date());

        ////
        repo = new RentalRepo();
        assertTrue(repo.getBookRentals().isEmpty());
        assertTrue(repo.getMovieRentals().isEmpty());
        repo.addBookRental(tester);
        repo.addBookRental(tester2);
        repo.addMovieRental(tester3);
    }

    @Test
    void getMovieRentals() {
        assertFalse(repo.getMovieRentals().isEmpty());
        assertEquals(NUMBER_OF_MOVIE_RENTALS, repo.getMovieRentals().size());
    }

    @Test
    void getBookRentals() {
        assertFalse(repo.getMovieRentals().isEmpty());
        assertEquals(NUMBER_OF_BOOK_RENTALS, repo.getBookRentals().size());
    }

    @Test
    void removeMovieRental() {
        final int repo_size = repo.getBookRentals().size();
        repo.removeBookRental(tester2);
        assertEquals(repo_size - 1, repo.getBookRentals().size());
    }

    @Test
    void removeBookRental() {
        final int repo_size = repo.getMovieRentals().size();
        repo.removeMovieRental(tester3);
        assertEquals(repo_size - 1, repo.getMovieRentals().size());
    }

    @Test
    void addMovieRental() {
        final int repo_size = repo.getMovieRentals().size();
        repo.addMovieRental(my_movie);
        assertEquals(repo_size + 1, repo.getMovieRentals().size());
    }

    @Test
    void addBookRental() {
        final int repo_size = repo.getBookRentals().size();
        my_book = new BookRental();
        repo.addBookRental(my_book);
        assertEquals(repo_size + 1, repo.getBookRentals().size());
    }

    @Test
    void getMovieRentalViaUUID() {
        MovieRental added = repo.addMovieRental(my_movie);
        MovieRental found = repo.getMovieRentalViaUUID(added.getId());
        assertEquals(my_movie, found);
    }

    @Test
    void getBookRentalViaUUID() {
        BookRental added = repo.addBookRental(my_book);
        BookRental found = repo.getBookRentalViaUUID(added.getId());
        assertEquals(my_book, found);
    }

    @Test
    void getMovieRental() {
        repo.addMovieRental(my_movie);
        MovieRental found = repo.getMovieRental(my_movie);
        assertEquals(my_movie, found);
    }

    @Test
    void getBookRental() {
        repo.addBookRental(my_book);
        BookRental found = repo.getBookRental(my_book);
        assertEquals(my_book, found);
    }

    @Test
    void updateSingleMovieRental() {

        MovieRental updated = repo.updateSingleMovieRental(tester3,my_movie);
        assertEquals(my_movie.getMovie(), updated.getMovie());
        assertNotEquals(my_movie.getId(), updated.getId());
    }

    @Test
    void updateSingleBookRental() {

        BookRental updated = repo.updateSingleBookRental(tester2,my_book);
        assertEquals(my_book.getBook(), updated.getBook());
        assertNotEquals(my_book.getId(), updated.getId());
    }

    @Test
    void getDisabledDays() {
        assertNotNull(repo.getDisabledDays());
    }
}