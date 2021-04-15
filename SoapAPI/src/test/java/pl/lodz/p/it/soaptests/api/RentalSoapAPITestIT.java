package pl.lodz.p.it.soaptests.api;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Formatter;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testcontainers
class RentalSoapAPITestIT {

    protected static String address = "http://localhost:8080/SoapAPI/RentalAPI";


    private static Network network = Network.newNetwork();

    static final Logger LOGGER_INSTANCE_1 = LoggerFactory.getLogger("instance1");

    @Container
    private static GenericContainer serviceOne = new GenericContainer(
            new ImageFromDockerfile()
                    .withDockerfileFromBuilder(builder
                            -> builder
                            .from("payara/server-full:5.2020.7-jdk11")
                            .copy("SoapAPI.war", "/opt/payara/deployments")
                            .build())
                    .withFileFromPath("SoapAPI.war", Path.of("target", "SoapAPI-1.0-SNAPSHOT.war"))
    )
            .withExposedPorts(8080, 4848)
            .waitingFor(Wait.forHttp("/SoapAPI/AccountAPI?wsdl")
                    .forPort(8080)
                    .forStatusCode(200));
    @BeforeAll
    public void setup(){
        Formatter formatter = new Formatter();
        String stringURL = formatter.format("http://localhost:%d/SoapAPI/RentalAPI", serviceOne.getMappedPort(8080)).toString();
        address = stringURL;
    }

    private String cachedId = "";

    private Element executePost(String resource, String arg0,String arg1, String altURL) throws IOException, ParserConfigurationException, SAXException {
        //Wczytanie z pliku xml:
        String content = new Scanner(new File(getClass().getClassLoader().getResource(resource).getFile())).useDelimiter("\\Z").next();
        if(null!=arg0)
            content = content.replaceAll("testArg0",arg0);
        if(null!=arg1)
            content = content.replaceAll("testArg1",arg1);
        //Przygotwanie post-a
        HttpClient client = HttpClients.createDefault();
        String addres_tmp;
        if(null!=altURL)
            addres_tmp=altURL;
        else
            addres_tmp = address;
        HttpPost httpPost = new HttpPost(addres_tmp);
        StringEntity stringEntity = new StringEntity(content);
        httpPost.setEntity(stringEntity);
        httpPost.addHeader("Content-Type", "text/xml");
        //Wykonanie
        HttpResponse response = client.execute(httpPost);
        //Pobranie zwróconego body
        String responseXML = EntityUtils.toString(response.getEntity());
        //Przygotawanie dokumentu xml z tego co zwrócił
        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        ByteArrayInputStream input = new ByteArrayInputStream(
                responseXML.getBytes("UTF-8"));
        Document doc = builder.parse(input);
        return doc.getDocumentElement();
    }

    @Test
    @Order(1)
    public void postMovieRentalTest() throws IOException, ParserConfigurationException, SAXException {
        String newPort = serviceOne.getMappedPort(8080).toString();
        Element element = executePost(
                "getMovies.xml",
                null,
                null,
                "http://localhost:"+newPort+"/SoapAPI/MovieAPI");
        String temp_movie_id = element.getElementsByTagName("id").item(0).getTextContent();

        element = executePost(
                "getAccounts.xml",
                null,
                null,
                "http://localhost:"+newPort+"/SoapAPI/AccountAPI");
        String temp_account_id = element.getElementsByTagName("id").item(0).getTextContent();

        element = executePost("postMovieRental.xml",temp_movie_id,temp_account_id,null);

        cachedId = element.getElementsByTagName("id").item(2).getTextContent();
        //Sprawdzanie poprawnego dodania
        element = executePost("getMoviesRentals.xml",null,null,null);
        assertEquals(2,element.getElementsByTagName("return").getLength());
    }

    @Test
    @Order(2)
    public void getMovieRentalTest() throws IOException, ParserConfigurationException, SAXException {
        //Wczytanie z pliku xml
        Element element = executePost("getMovieRental.xml",cachedId,null,null);
        //Asercje
        assertEquals("Kowalski",element.getElementsByTagName("lastName").item(0).getTextContent());
        assertEquals("The Godfather",element.getElementsByTagName("title").item(0).getTextContent());
    }

    @Test
    @Order(3)
    public void updateMovieRentalTest() throws IOException, ParserConfigurationException, SAXException {
        Element element = executePost("updateMovieRental.xml",cachedId,null,null);
        //Asercje
        assertEquals("OK",element.getElementsByTagName("return").item(0).getTextContent());

        element = executePost("getMovieRental.xml",cachedId,null,null);
        assertEquals("hello",element.getElementsByTagName("lastName").item(0).getTextContent());
    }

    @Test
    @Order(4)
    public void deleteMovieRentalTest() throws IOException, ParserConfigurationException, SAXException {
        Element element = executePost("deleteMovieRental.xml",cachedId,null,null);
        //Asercje
        assertEquals("OK",element.getElementsByTagName("return").item(0).getTextContent());

        element = executePost("getMoviesRentals.xml",null,null,null);
        assertEquals(1,element.getElementsByTagName("return").getLength());
    }
    @Test
    @Order(5)
    public void postBookRentalTest() throws IOException, ParserConfigurationException, SAXException {
        String newPort = serviceOne.getMappedPort(8080).toString();
        Element element = executePost(
                "getBooks.xml",
                null,
                null,
                "http://localhost:"+newPort+"/SoapAPI/BookAPI");
        String temp_book_id = element.getElementsByTagName("id").item(0).getTextContent();

        element = executePost(
                "getAccounts.xml",
                null,
                null,
                "http://localhost:"+newPort+"/SoapAPI/AccountAPI");
        String temp_account_id = element.getElementsByTagName("id").item(0).getTextContent();

        element = executePost("postBookRental.xml",temp_book_id,temp_account_id,null);

        cachedId = element.getElementsByTagName("id").item(2).getTextContent();
        //Sprawdzanie poprawnego dodania
        element = executePost("getBooksRentals.xml",null,null,null);
        assertEquals(2,element.getElementsByTagName("return").getLength());
    }

    @Test
    @Order(6)
    public void getBookRentalTest() throws IOException, ParserConfigurationException, SAXException {
        //Wczytanie z pliku xml
        Element element = executePost("getBookRental.xml",cachedId,null,null);
        //Asercje
        assertEquals("Kowalski",element.getElementsByTagName("lastName").item(0).getTextContent());
        assertEquals("Doctor Sleep",element.getElementsByTagName("title").item(0).getTextContent());
    }

    @Test
    @Order(7)
    public void updateBookRentalTest() throws IOException, ParserConfigurationException, SAXException {
        Element element = executePost("updateBookRental.xml",cachedId,null,null);
        //Asercje
        assertEquals("OK",element.getElementsByTagName("return").item(0).getTextContent());

        element = executePost("getBookRental.xml",cachedId,null,null);
        assertEquals("hello",element.getElementsByTagName("lastName").item(0).getTextContent());
    }

    @Test
    @Order(8)
    public void deleteBookRentalTest() throws IOException, ParserConfigurationException, SAXException {
        Element element = executePost("deleteBookRental.xml",cachedId,null,null);
        //Asercje
        assertEquals("OK",element.getElementsByTagName("return").item(0).getTextContent());

        element = executePost("getBooksRentals.xml",null,null,null);
        assertEquals(1,element.getElementsByTagName("return").getLength());
    }
}