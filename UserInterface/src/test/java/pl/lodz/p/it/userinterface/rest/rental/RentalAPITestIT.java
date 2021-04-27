package pl.lodz.p.it.userinterface.rest.rental;

import org.junit.jupiter.api.*;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.lodz.p.it.rentviewmodel.modelDTO.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Formatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testcontainers
class RentalAPITestIT {
    private String cachedId;
    private WebTarget target;
    private URL serviceURL;

    @Container
    private static GenericContainer serviceOne = new GenericContainer(
            new ImageFromDockerfile()
                    .withDockerfileFromBuilder(builder
                            -> builder
                            .from("payara/server-full:5.2020.7-jdk11")
                            .copy("UserInterface.war", "/opt/payara/deployments")
                            .build())
                    .withFileFromPath("UserInterface.war", Path.of("target", "UserInterface-1.0-SNAPSHOT.war"))
    )
            .withExposedPorts(8080, 4848)
            .waitingFor(Wait.forHttp("/shop/rest/accounts")
                    .forPort(8080)
                    .forStatusCode(200));
    @BeforeAll
    public void setup() throws MalformedURLException, URISyntaxException {
        Formatter formatter = new Formatter();
        String stringURL = formatter.format("http://localhost:%d/shop/rest", serviceOne.getMappedPort(8080)).toString();
        serviceURL = new URL(stringURL);
        Client client = ClientBuilder.newClient();
        target = client.target(serviceURL.toURI());
    }

    private final AccountDTO accountDTO = new AccountDTO("test", "Testowy", "user", true, "test", "test123");
    private final MovieDTO movieDTO = new MovieDTO("Test", "test", 7.9,false);
    private final MovieRentalDTO testMovieRentalDTO = new MovieRentalDTO(movieDTO, accountDTO);
    private final BookDTO bookDTO = new BookDTO("Test", "test", 100,false);
    private final BookRentalDTO testBookRentalDTO = new BookRentalDTO(bookDTO, accountDTO);

    @Test
    @Order(1)
    void addSingleBookRentalToStorage() {
        Response getResponse = target.path("rentals")
                .path("book")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(testBookRentalDTO));
        assertEquals(Response.Status.CREATED.getStatusCode(),  getResponse.getStatus());
        BookRentalDTO readEntity = getResponse.readEntity(BookRentalDTO.class);
        cachedId = readEntity.getId();
        assertEquals(testBookRentalDTO.getBook(), readEntity.getBook());
    }

    @Test
    @Order(2)
    void getBooksRentalsFromStorage() {
        Response getResponse = target.path("rentals")
                .path("book")
                .request(MediaType.APPLICATION_JSON)
                .get();
        List<BookRentalDTO> readEntity = getResponse.readEntity(new GenericType<List<BookRentalDTO>>() {});
        assertFalse(readEntity.isEmpty());
    }

    @Test
    @Order(3)
    void updateSingleBookRentalFromStorage() {
        Response getResponse = target
                .path("rentals")
                .path("book")
                .path("update")
                .path(cachedId)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(testBookRentalDTO));
        assertEquals(Response.Status.OK.getStatusCode(),  getResponse.getStatus());
    }

    @Test
    @Order(4)
    void removeSingleBookRentalFromStorage() {
        Response getResponse = target
                .path("rentals")
                .path("book")
                .path("del")
                .path(cachedId)
                .request(MediaType.APPLICATION_JSON)
                .delete();
        assertEquals(Response.Status.OK.getStatusCode(),  getResponse.getStatus());
    }

    @Test
    @Order(5)
    void addSingleMovieRentalToStorage() {
        Response getResponse = target.path("rentals")
                .path("movie")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(testMovieRentalDTO));
        assertEquals(Response.Status.CREATED.getStatusCode(),  getResponse.getStatus());
        MovieRentalDTO readEntity = getResponse.readEntity(MovieRentalDTO.class);
        cachedId = readEntity.getId();
        assertEquals(testMovieRentalDTO.getMovie(), readEntity.getMovie());
    }

    @Test
    @Order(6)
    void getMoviesRentalsFromStorage() {
        Response getResponse = target.path("rentals")
                .path("movie")
                .request(MediaType.APPLICATION_JSON)
                .get();
        List<MovieRentalDTO> readEntity = getResponse.readEntity(new GenericType<List<MovieRentalDTO>>() {});
        assertFalse(readEntity.isEmpty());
    }

    @Test
    @Order(7)
    void updateSingleMovieRentalFromStorage() {
        Response getResponse = target
                .path("rentals")
                .path("movie")
                .path("update")
                .path(cachedId)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(testMovieRentalDTO));
        assertEquals(Response.Status.OK.getStatusCode(),  getResponse.getStatus());
    }

    @Test
    @Order(8)
    void removeSingleMovieRentalFromStorage() {
        Response getResponse = target
                .path("rentals")
                .path("movie")
                .path("del")
                .path(cachedId)
                .request(MediaType.APPLICATION_JSON)
                .delete();
        assertEquals(Response.Status.OK.getStatusCode(),  getResponse.getStatus());
    }
}