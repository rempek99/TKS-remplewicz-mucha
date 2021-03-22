package repositories;

import model_ent.entities.*;
import model_ent.repositories.RentalEntRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RentalEntRepoTest {

    private static final int NUMBER_OF_BOOK_RENTALS = 2;
    private static final int NUMBER_OF_MOVIE_RENTALS = 1;


    private RentalEntRepo repo;

    private final MovieEnt testMovie = new MovieEnt("Test", "Tester", 100,false);
    private final BookEnt testBook = new BookEnt("Test", "Tester", 100,false);
    private final AccountEnt testAccount = new AccountEnt("Tester", "Testowy", "user", true, "test", "test123");
    private MovieRentalEnt my_movie;
    private BookRentalEnt my_book;

    private final BookRentalEnt tester = new BookRentalEnt(null, null);
    private final BookRentalEnt tester2 = new BookRentalEnt(null, null);
    private final MovieRentalEnt tester3 = new MovieRentalEnt(null, null);

    @BeforeAll
    void initTesters() {
        tester.setBookEnt(testBook);
        tester.setAccountEnt(testAccount);
        tester.setRentalStart(new Date());
        tester.setRange(new ArrayList<>());
        tester.setRentalEnd(new Date());

        tester2.setBookEnt(testBook);
        tester2.setAccountEnt(testAccount);
        tester2.setRentalStart(new Date());
        tester2.setRange(new ArrayList<>());
        tester2.setRentalEnd(new Date());

        tester3.setMovieEnt(testMovie);
        tester3.setAccountEnt(testAccount);
        tester3.setRentalStart(new Date());
        tester3.setRange(new ArrayList<>());
        tester3.setRentalEnd(new Date());
    }

    @BeforeEach
    void initRepo() {
        my_movie = new MovieRentalEnt(null, null);
        my_movie.setMovieEnt(new MovieEnt("trelo","morelo", 0.3,false));
        my_movie.setAccountEnt(testAccount);
        my_movie.setRentalStart(new Date());
        my_movie.setRange(new ArrayList<>());
        my_movie.setRentalEnd(new Date());
        my_movie.setId("0000-0000");
//
        my_book = new BookRentalEnt(null, null);
        my_book.setBookEnt(new BookEnt("trelo","morelo", 103,false));
        my_book.setAccountEnt(testAccount);
        my_book.setRentalStart(new Date());
        my_book.setRange(new ArrayList<>());
        my_book.setRentalEnd(new Date());
        my_book.setId("0000-0001");

        ////
        repo = new RentalEntRepo();
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
        my_book = new BookRentalEnt(null, null);
        repo.addBookRental(my_book);
        assertEquals(repo_size + 1, repo.getBookRentals().size());
    }

    @Test
    void getMovieRentalViaUUID() {
        MovieRentalEnt added = repo.addMovieRental(my_movie);
        MovieRentalEnt found = repo.getMovieRentalViaUUID(added.getId());
        assertEquals(my_movie, found);
    }

    @Test
    void getBookRentalViaUUID() {
        BookRentalEnt added = repo.addBookRental(my_book);
        BookRentalEnt found = repo.getBookRentalViaUUID(added.getId());
        assertEquals(my_book, found);
    }

    @Test
    void getMovieRental() {
        repo.addMovieRental(my_movie);
        MovieRentalEnt found = repo.getMovieRental(my_movie);
        assertEquals(my_movie, found);
    }

    @Test
    void getBookRental() {
        repo.addBookRental(my_book);
        BookRentalEnt found = repo.getBookRental(my_book);
        assertEquals(my_book, found);
    }

    @Test
    void updateSingleMovieRental() {

        MovieRentalEnt updated = repo.updateSingleMovieRental(tester3,my_movie);
        assertEquals(my_movie.getMovieEnt(), updated.getMovieEnt());
        assertNotEquals(my_movie.getId(), updated.getId());
    }

    @Test
    void updateSingleBookRental() {

        BookRentalEnt updated = repo.updateSingleBookRental(tester2,my_book);
        assertEquals(my_book.getBookEnt(), updated.getBookEnt());
        assertNotEquals(my_book.getId(), updated.getId());
    }

    @Test
    void getDisabledDays() {
        assertNotNull(repo.getDisabledDays());
    }
}