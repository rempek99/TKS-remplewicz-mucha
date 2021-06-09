package pl.lodz.p.it.webapplication.endpoints;

import pl.lodz.p.it.webapplication.kafka.KafkaUserProducer;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@RequestScoped
@Path("user")
public class UserEndpoint {

    private final KafkaUserProducer userProducer = new KafkaUserProducer();

    @POST
    public void createUser(){
        userProducer.sendCreateUserEvent();
    }
}
