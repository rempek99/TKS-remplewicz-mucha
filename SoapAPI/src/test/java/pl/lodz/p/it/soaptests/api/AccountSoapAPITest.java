package pl.lodz.p.it.soaptests.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
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

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

class AccountSoapAPITest {

    protected static String address = "http://localhost:8080/ws";

    private Element executePost(String resource) throws IOException, ParserConfigurationException, SAXException {
        //Wczytanie z pliku xml:
        String content = new Scanner(new File(getClass().getClassLoader().getResource(resource).getFile())).useDelimiter("\\Z").next();
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
    public void postTest() throws IOException, ParserConfigurationException, SAXException {

        Element element = executePost("postAccount.xml");
        //Asercje
        Assertions.assertEquals(element.getElementsByTagName("ns2:id").item(0).getTextContent(), "test1");

        //Sprawdzanie poprawnego dodania
        element = executePost("getAccounts.xml");
        Assertions.assertEquals(element.getElementsByTagName("ns2:account").getLength(), 6);
    }

    @Test
    @Order(2)
    public void getTest() throws IOException, ParserConfigurationException, SAXException {
        //Wczytanie z pliku xml
        Element element = executePost("getAccount.xml");
        //Asercje
        Assertions.assertEquals(element.getElementsByTagName("ns2:lastName").item(0).getTextContent(), "Testowy");
    }

    @Test
    @Order(3)
    public void wrongPostTest() throws IOException, ParserConfigurationException, SAXException {
        Element element = executePost("postAccount.xml");
        //Asercje
        Assertions.assertEquals(element.getElementsByTagName("ns2:id").item(0).getTextContent(), "IM SO SORRY");
        //Sprawdzenie niedodania elementu
        element = executePost("getAccounts.xml");
        Assertions.assertEquals(element.getElementsByTagName("ns2:account").getLength(), 6);
    }

    @Test
    @Order(4)
    public void deleteTest() throws IOException, ParserConfigurationException, SAXException {
        Element element = executePost("deleteAccount.xml");
        //Asercje
        Assertions.assertEquals(element.getElementsByTagName("ns2:info").item(0).getTextContent(), "OK");

        element = executePost("getAccounts.xml");
        Assertions.assertEquals(element.getElementsByTagName("ns2:account").getLength(), 5);
    }
}