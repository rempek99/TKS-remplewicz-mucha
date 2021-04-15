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
class MovieSoapAPITestIT {

    protected static String address = "http://localhost:8080/SoapAPI/MovieAPI";

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
        String stringURL = formatter.format("http://localhost:%d/SoapAPI/MovieAPI", serviceOne.getMappedPort(8080)).toString();
        address = stringURL;
    }


    private String cachedId = "";

    private Element executePost(String resource, String arg0) throws IOException, ParserConfigurationException, SAXException {
        //Wczytanie z pliku xml:
        String content = new Scanner(new File(getClass().getClassLoader().getResource(resource).getFile())).useDelimiter("\\Z").next();
        if(null!=arg0)
            content = content.replaceAll("testArg0",arg0);
        //Przygotwanie post-a
        HttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(address);
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
    @Order(2)
    public void postMovieRentalTest() throws IOException, ParserConfigurationException, SAXException {

        Element element = executePost("postMovie.xml",null);
        cachedId = element.getElementsByTagName("id").item(0).getTextContent();
        //Asercje
        assertEquals("test123",element.getElementsByTagName("title").item(0).getTextContent());

        //Sprawdzanie poprawnego dodania
        element = executePost("getMovie.xml",cachedId);
        assertEquals("Testowy",element.getElementsByTagName("author").item(0).getTextContent());
    }

    @Test
    @Order(3)
    public void getTest() throws IOException, ParserConfigurationException, SAXException {
        //Wczytanie z pliku xml
        Element element = executePost("getMovie.xml",cachedId);
        //Asercje
        assertEquals("Testowy",element.getElementsByTagName("author").item(0).getTextContent());
    }

    @Test
    @Order(4)
    public void wrongPostTest() throws IOException, ParserConfigurationException, SAXException {
        Element element = executePost("postMovie.xml",null);
        //Asercje
        assertEquals(element.getElementsByTagName("message").item(0).getTextContent(), "Duplicated");
        //Sprawdzenie niedodania elementu
        element = executePost("getMovies.xml",null);
        assertEquals(11, element.getElementsByTagName("return").getLength());
    }

    @Test
    @Order(5)
    public void deleteTest() throws IOException, ParserConfigurationException, SAXException {
        Element element = executePost("deleteMovie.xml",cachedId);
        //Asercje
        assertEquals("OK",element.getElementsByTagName("return").item(0).getTextContent());

        element = executePost("getMovies.xml",null);
        assertEquals(10,element.getElementsByTagName("return").getLength());
    }
}