package rest;

import model.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.*;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class RestAPI implements Serializable {

    Client client = ClientBuilder.newClient();
    private WebTarget baseURI = client.target("http://desktop-j93pug2:8080/ApplicationCore/rest");

    // GETs

    public List<Account> getAllAccounts() {
        return baseURI.path("accounts").request(MediaType.APPLICATION_JSON).get(new GenericType<List<Account>>() {});
    }

    public Account getSingleAccount(String str) {
        return baseURI.path("accounts/" + str).request(MediaType.APPLICATION_JSON).get(Account.class);
    }

    public List<Movie> getAllMovies() {
        return baseURI.path("movies").request(MediaType.APPLICATION_JSON).get(new GenericType<List<Movie>>() {});
    }

    public List<Book> getAllBooks() {
        return baseURI.path("books").request(MediaType.APPLICATION_JSON).get(new GenericType<List<Book>>() {});
    }

    public List<BookRental> getAllBookRentals() {
        return baseURI.path("bookRentals").request(MediaType.APPLICATION_JSON).get(new GenericType<List<BookRental>>() {});
    }

    public List<MovieRental> getAllMovieRentals() {
        return baseURI.path("movieRentals").request(MediaType.APPLICATION_JSON).get(new GenericType<List<MovieRental>>() {});
    }

    // DELETEs

    public void deleteAccount(String str) {
        Response response = baseURI.path("accounts/del/" + str).request(MediaType.APPLICATION_JSON).delete();
        System.out.println(response);
    }

    public void deleteMovie(String str) {
        Response response = baseURI.path("movies/del/" + str).request(MediaType.APPLICATION_JSON).delete();
        System.out.println(response);
    }

    public void deleteBook(String str) {
        Response response = baseURI.path("books/del/" + str).request(MediaType.APPLICATION_JSON).delete();
        System.out.println(response);
    }

    public void deleteBookRental(String str) {
        Response response = baseURI.path("bookRentals/del/" + str).request(MediaType.APPLICATION_JSON).delete();
        System.out.println(response);
    }

    public void deleteMovieRental(String str) {
        Response response = baseURI.path("movieRentals/del/" + str).request(MediaType.APPLICATION_JSON).delete();
        System.out.println(response);
    }

    //POSTs

    public void addAccount(Account account) {
        Response response = baseURI.path("accounts").request().post(Entity.entity(account, MediaType.APPLICATION_JSON));
        System.out.println(response);
    }

    public void addBook(Book book) {
        Response response = baseURI.path("books").request().post(Entity.entity(book, MediaType.APPLICATION_JSON));
        System.out.println(response);
    }

    public void addMovie(Movie movie) {
        Response response = baseURI.path("movies").request().post(Entity.entity(movie, MediaType.APPLICATION_JSON));
        System.out.println(response);
    }

    public void addMovieRental(MovieRental movieRental) {
        Response response = baseURI.path("movieRentals").request().post(Entity.entity(movieRental, MediaType.APPLICATION_JSON));
        System.out.println(response);
    }

    public void addBookRental(BookRental bookRental) {
        Response response = baseURI.path("bookRentals").request().post(Entity.entity(bookRental, MediaType.APPLICATION_JSON));
        System.out.println(response);
    }

    //PUTs

    public void updateAccount(String str, Account newAccount){
        Response response = baseURI.path("accounts/update/" + str).request().put(Entity.entity(newAccount, MediaType.APPLICATION_JSON));
        System.out.println(response);
    }

    public void updateBook(String str, Book newBook){
        Response response = baseURI.path("books/update/" + str).request().put(Entity.entity(newBook, MediaType.APPLICATION_JSON));
        System.out.println(response);
    }

    public void updateMovie(String str, Movie newMovie){
        Response response = baseURI.path("movies/update/" + str).request().put(Entity.entity(newMovie, MediaType.APPLICATION_JSON));
        System.out.println(response);
    }

    public void updateBookRental(String str, BookRental newBookRental){
        Response response = baseURI.path("bookRentals/update/" + str).request().put(Entity.entity(newBookRental, MediaType.APPLICATION_JSON));
        System.out.println(response);
    }

    public void updateMovieRental(String str, MovieRental newMovieRental){
        Response response = baseURI.path("movieRentals/update/" + str).request().put(Entity.entity(newMovieRental, MediaType.APPLICATION_JSON));
        System.out.println(response);
    }
}
