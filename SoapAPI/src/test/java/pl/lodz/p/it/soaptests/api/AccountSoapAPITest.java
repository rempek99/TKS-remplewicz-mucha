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
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountSoapAPITest {

    protected static String address = "http://localhost:8080/SoapAPI-1.0-SNAPSHOT/AccountAPI";

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
    @Order(1)
    public void helloTest() throws IOException, ParserConfigurationException, SAXException {
        Element element = executePost("hello.xml", null);
        assertEquals("Hello Soap"
                , element.getElementsByTagName("ns2:helloResponse").item(0).getTextContent());
    }

    @Test
    @Order(2)
    public void postTest() throws IOException, ParserConfigurationException, SAXException {

        Element element = executePost("postAccount.xml",null);
        cachedId = element.getElementsByTagName("id").item(0).getTextContent();
        //Asercje
        assertEquals("test123",element.getElementsByTagName("login").item(0).getTextContent());

        //Sprawdzanie poprawnego dodania
        element = executePost("getAccount.xml",cachedId);
        assertEquals("Testowy",element.getElementsByTagName("lastName").item(0).getTextContent());
    }

    @Test
    @Order(3)
    public void getTest() throws IOException, ParserConfigurationException, SAXException {
        //Wczytanie z pliku xml
        Element element = executePost("getAccount.xml",cachedId);
        //Asercje
        assertEquals("Testowy",element.getElementsByTagName("lastName").item(0).getTextContent());
    }

    @Test
    @Order(4)
    public void wrongPostTest() throws IOException, ParserConfigurationException, SAXException {
        Element element = executePost("postAccount.xml",null);
        //Asercje
        assertEquals(element.getElementsByTagName("message").item(0).getTextContent(), "Duplicated");
        //Sprawdzenie niedodania elementu
        element = executePost("getAccounts.xml",null);
        assertEquals(element.getElementsByTagName("return").getLength(), 6);
    }

    @Test
    @Order(5)
    public void deleteTest() throws IOException, ParserConfigurationException, SAXException {
        Element element = executePost("deleteAccount.xml",cachedId);
        //Asercje
        assertEquals(element.getElementsByTagName("return").item(0).getTextContent(), "OK");

        element = executePost("getAccounts.xml",null);
        assertEquals(element.getElementsByTagName("return").getLength(), 5);
    }
}