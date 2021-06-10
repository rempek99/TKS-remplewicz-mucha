package pl.lodz.p.it.webapplication.endpoints;

import pl.lodz.p.it.topicmodels.dtos.BookDTO;
import pl.lodz.p.it.webapplication.kafka.KafkaBookProducer;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@RequestScoped
@Path("book")
public class BookEndpoint {

    private final KafkaBookProducer bookProducer = new KafkaBookProducer();

    @POST
    @Consumes(APPLICATION_JSON)
    public void createBook(BookDTO book){
        bookProducer.sendCreateBookEvent(book);
    }
}
