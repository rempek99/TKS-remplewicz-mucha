package services;

import aggregates.adapters.RentalRepoAdapter;
import infrastructure.RentalPort;
import model.*;
import model_ent.repositories.RentalEntRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class RentalServiceTest {

    @InjectMocks
    private RentalService rentalService;

    @Mock(name = "rentalPort")
    private RentalPort rentalPort;

    private final Movie testMovie = new Movie("Test", "Tester", 100, false);
    private final Movie testMovie2 = new Movie("Test2", "Tester2", 200, true);
    private final Movie testMovie3 = new Movie("Test3", "Tester3", 300, true);

    private final Book testBook = new Book("Test", "Tester", 100, false);
    private final Book testBook2 = new Book("Test2", "Tester2", 200, true);
    private final Book testBook3 = new Book("Test3", "Tester3", 300, true);

    private final Account tester = new Account("Tester", "Testowy", "user", true, "test", "test123");
    private final Account tester2 = new Account("Tester2", "Testowy2", "user", true, "test2", "test1234");
    private final Account tester3 = new Account("Tester3", "Testowy3", "user", true, "test3", "test12345");
    private final BookRental testBookRental = new BookRental(testBook, tester);
    private final MovieRental testMovieRental = new MovieRental(testMovie, tester);

    @Test
    void getAllMovieRentals() {
        //given
        //when
        rentalService.getAllMovieRentals();
        //then
        then(rentalPort).should().getMovieRentals();
        then(rentalPort).shouldHaveNoMoreInteractions();
    }

    @Test
    void getAllBookRentals() {
        //given
        //when
        rentalService.getAllBookRentals();
        //then
        then(rentalPort).should().getBookRentals();
        then(rentalPort).shouldHaveNoMoreInteractions();
    }

    @Test
    void addMovieRental() {
        //given
        MovieRental exampleMovieRental = testMovieRental;
        //when
        rentalService.addMovieRental(exampleMovieRental);
        //then
        then(rentalPort).should().addMovieRental(exampleMovieRental);
    }

    @Test
    void addBookRental() {
        //given
        BookRental exampleBookRental = testBookRental;
        //when
        rentalService.addBookRental(exampleBookRental);
        //then
        then(rentalPort).should().addBookRental(exampleBookRental);
        then(rentalPort).shouldHaveNoMoreInteractions();
    }

    @Test
    void removeMovieRental() {
        //given
        MovieRental exampleMovieRental = testMovieRental;
        //when
        rentalService.removeMovieRental(exampleMovieRental);
        //then
        then(rentalPort).should().removeMovieRental(exampleMovieRental);
        then(rentalPort).shouldHaveNoMoreInteractions();
    }

    @Test
    void removeBookRental() {
        //given
        BookRental exampleBookRental = testBookRental;
        //when
        rentalService.removeBookRental(exampleBookRental);
        //then
        then(rentalPort).should().removeBookRental(exampleBookRental);
        then(rentalPort).shouldHaveNoMoreInteractions();
    }

    @Test
    void getMovieRentalViaUUID() {
        //given
        MovieRental exampleMovieRental = testMovieRental;
        given(rentalPort.getMovieRentalViaUUID(exampleMovieRental.getId())).willReturn(exampleMovieRental);
        //when
        MovieRental foundMovieRentalViaUUID = rentalService.getMovieRentalViaUUID(exampleMovieRental.getId());
        //then
        then(rentalPort).should().getMovieRentalViaUUID(exampleMovieRental.getId());
        then(rentalPort).shouldHaveNoMoreInteractions();
        assertEquals(exampleMovieRental, foundMovieRentalViaUUID);
    }

    @Test
    void getBookRentalViaUUID() {
        //given
        BookRental exampleBookRental = testBookRental;
        given(rentalPort.getBookRentalViaUUID(exampleBookRental.getId())).willReturn(exampleBookRental);
        //when
        BookRental foundBookRentalViaUUID = rentalService.getBookRentalViaUUID(exampleBookRental.getId());
        //then
        then(rentalPort).should().getBookRentalViaUUID(exampleBookRental.getId());
        then(rentalPort).shouldHaveNoMoreInteractions();
        assertEquals(exampleBookRental, foundBookRentalViaUUID);
    }

    @Test
    void updateSingleBookRental() {
        //given
        BookRental exampleBookRental = testBookRental;
        BookRental newBookRental = new BookRental(testBook2, tester);
        //when
        rentalService.updateSingleBookRental(exampleBookRental, newBookRental);
        //then
        then(rentalPort).should().updateSingleBookRental(exampleBookRental,newBookRental);
        then(rentalPort).shouldHaveNoMoreInteractions();
    }

    @Test
    void updateSingleMovieRental() {
        //given
        MovieRental exampleMovieRental = testMovieRental;
        MovieRental newMovieRental = new MovieRental(testMovie2, tester);
        //when
        rentalService.updateSingleMovieRental(exampleMovieRental, newMovieRental);
        //then
        then(rentalPort).should().updateSingleMovieRental(exampleMovieRental,newMovieRental);
        then(rentalPort).shouldHaveNoMoreInteractions();
    }

    @Test
    void getDisabledDays() {
        assertNotNull(rentalService.getDisabledDays());
    }
}