package pl.lodz.p.it.rentapplicationapi.rest.book;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import pl.lodz.p.it.rentapplicationapi.aggregates.adapters.BookServiceAdapter;
import pl.lodz.p.it.rentapplicationapi.rentviewmodel.modelDTO.BookDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.Optional;
import java.util.Properties;

@RequestScoped
@Path("books")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class BookAPI {

    @Inject
    BookServiceAdapter bookAdapter;
    private final KafkaProducer<String,byte[]> producer;
    private final Properties properties;

    @Inject
    public BookAPI() {
        // KafkaConfiguration
        properties = new Properties();
        properties.setProperty("bootstrap.servers","localhost:9092");
        properties.setProperty("kafka.topic.name", "tks-shop");
        producer = new KafkaProducer<String, byte[]>(
                properties,
                new StringSerializer(),
                new ByteArraySerializer());

    }

    // Queries

    @GET
    public Response getBooksFromStorage() {
        return Response.ok().entity(bookAdapter.getAllBooks()).status(Response.Status.OK).build();
    }

    // Commands

    @POST
    public Response addSingleBookToStorage(BookDTO book) {
        BookDTO bookDTO = bookAdapter.addBook(book);
        return Response
                .ok()
                //.entity(bookDTO)
                .status(Response.Status.CREATED)
                .build();
    }

    @DELETE
    @Path("del/{str}")
    public Response removeSingleBookFromStorage(@PathParam("str") String str) {
        Optional<BookDTO> book = Optional.ofNullable(bookAdapter.getBookViaUUID(str));
        if(book.isPresent()){
            bookAdapter.removeBook(book.get());
            return Response.ok().entity("Success").status(Response.Status.OK).build();
        }
        else{
            return Response.status(Response.Status.NO_CONTENT).entity("Wrong input data").build();
        }
    }

    @GET
    @Path("kafka/ping")
    public Response ping(){
        byte[] payload = ("Message from RentApplication | " + new Date()).getBytes();
        System.out.println("\n\n___\tMessange sent to kafka!\t___\n\n");
        ProducerRecord<String,byte[]> record = new ProducerRecord<>(
                properties.getProperty("kafka.topic.name"),
                payload
        );
        producer.send(record);
        return Response.ok().entity(payload).build();
    }

    @PUT
    @Path("update/{str}")
    public Response updateSingleBook(@PathParam("str") String str, BookDTO desiredBook) {
        BookDTO bookToChange = bookAdapter.getBookViaUUID(str);
        bookAdapter.updateSingleBook(bookToChange, desiredBook);
        return Response.ok().entity("Book updated succesfully!").status(Response.Status.OK).build();
    }

}
