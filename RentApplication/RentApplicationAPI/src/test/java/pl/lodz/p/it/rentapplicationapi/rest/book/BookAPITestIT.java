package pl.lodz.p.it.rentapplicationapi.rest.book;

import org.junit.jupiter.api.*;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.lodz.p.it.rentapplicationapi.rentviewmodel.modelDTO.BookDTO;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testcontainers
class BookAPITestIT {

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

    @Test
    @Order(2)
    void getBooksFromStorage() {
        Response getResponse = target.path("books")
                .request(MediaType.APPLICATION_JSON)
                .get();
        List<BookDTO> readEntity = getResponse.readEntity(new GenericType<List<BookDTO>>() {});
        assertFalse(readEntity.isEmpty());
    }

    @Test
    @Order(1)
    void addSingleBookToStorage() {
        BookDTO test = new BookDTO("test", "testowy", 100,false);
        Response getResponse = target.path("books")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(test));
        BookDTO readEntity = getResponse.readEntity(BookDTO.class);
        cachedId = readEntity.getId();
        assertEquals(test.getAuthor(), readEntity.getAuthor());
    }

    @Test
    @Order(4)
    void removeSingleBookFromStorage() {
        Response getResponse = target
                .path("books")
                .path("del")
                .path(cachedId)
                .request(MediaType.APPLICATION_JSON)
                .delete();
        assertEquals(Response.Status.OK.getStatusCode(),  getResponse.getStatus());
    }

    @Test
    @Order(3)
    void updateSingleBook() {
        BookDTO test = new BookDTO("test", "testowy", 100,false);
        Response getResponse = target
                .path("books")
                .path("update")
                .path(cachedId)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(test));
        assertEquals(Response.Status.OK.getStatusCode(),  getResponse.getStatus());
    }
}