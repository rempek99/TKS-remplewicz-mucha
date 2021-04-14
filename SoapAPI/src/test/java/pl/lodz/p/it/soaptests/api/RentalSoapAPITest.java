package pl.lodz.p.it.soaptests.api;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RentalSoapAPITest {

    protected static String address = "http://localhost:8080/SoapAPI-1.0-SNAPSHOT/RentalAPI";

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
    @Order(2)
    public void postTest() throws IOException, ParserConfigurationException, SAXException {
        Element element = executePost(
                "getMoviesRentals.xml",
                null,
                null,
                "http://localhost:8080/SoapAPI-1.0-SNAPSHOT/MovieAPI");
        String temp_movie_id = element.getElementsByTagName("id").item(0).getTextContent();

        element = executePost(
                "getMoviesRentals.xml",
                null,
                null,
                "http://localhost:8080/SoapAPI-1.0-SNAPSHOT/AccountAPI");
        String temp_account_id = element.getElementsByTagName("id").item(0).getTextContent();

        element = executePost("postMovieRental.xml",temp_movie_id,temp_account_id,null);

        cachedId = element.getElementsByTagName("id").item(0).getTextContent();
        //Sprawdzanie poprawnego dodania
        element = executePost("getMovieRental.xml",cachedId,null,null);
    }

//    @Test
//    @Order(3)
//    public void getTest() throws IOException, ParserConfigurationException, SAXException {
//        //Wczytanie z pliku xml
//        Element element = executePost("getMovie.xml",cachedId);
//        //Asercje
//        assertEquals("Testowy",element.getElementsByTagName("author").item(0).getTextContent());
//    }
//
//    @Test
//    @Order(4)
//    public void wrongPostTest() throws IOException, ParserConfigurationException, SAXException {
//        Element element = executePost("postMovie.xml",null);
//        //Asercje
//        assertEquals(element.getElementsByTagName("message").item(0).getTextContent(), "Duplicated");
//        //Sprawdzenie niedodania elementu
//        element = executePost("getMovies.xml",null);
//        assertEquals(11, element.getElementsByTagName("return").getLength());
//    }
//
//    @Test
//    @Order(5)
//    public void deleteTest() throws IOException, ParserConfigurationException, SAXException {
//        Element element = executePost("deleteMovie.xml",cachedId);
//        //Asercje
//        assertEquals("OK",element.getElementsByTagName("return").item(0).getTextContent());
//
//        element = executePost("getMovies.xml",null);
//        assertEquals(10,element.getElementsByTagName("return").getLength());
//    }
}