package pl.lodz.p.it.webapplication.endpoints;

import pl.lodz.p.it.topicmodels.dtos.UserDTO;
import pl.lodz.p.it.webapplication.kafka.KafkaUserProducer;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@RequestScoped
@Path("user")
public class UserEndpoint {

    private final KafkaUserProducer userProducer = new KafkaUserProducer();

    @POST
    @Consumes(APPLICATION_JSON)
    public void createUser(UserDTO user){
        userProducer.sendCreateUserEvent(user);
    }
}
