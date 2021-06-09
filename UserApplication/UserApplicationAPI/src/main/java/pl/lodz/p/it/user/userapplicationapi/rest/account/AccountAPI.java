package pl.lodz.p.it.user.userapplicationapi.rest.account;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import pl.lodz.p.it.user.userapplicationapi.aggregates.adapters.AccountServiceAdapter;
import pl.lodz.p.it.user.userapplicationapi.modelDTO.AccountDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.util.Date;
import java.util.Optional;
import java.util.Properties;

@RequestScoped
@Path("accounts")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class AccountAPI implements AutoCloseable{

    @Inject
    private AccountServiceAdapter accountAdapter;
    private final KafkaProducer<String,byte[]> producer;
    private final Properties properties;

    @Inject
    public AccountAPI() {
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
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccFromStorage() {
        return Response
                .ok()
                .entity(accountAdapter.getAllAccounts())
                .status(Response.Status.OK)
                .build();
    }

    @GET
    @Path("{str}")
    public Response getSingleAccFromStorage(@PathParam("str") String str) {
        return Response
                .ok()
                .entity(accountAdapter.getAccountViaUUID(str))
                .status(Response.Status.OK)
                .build();
    }

    @GET
    @Path("kafka/ping")
    public Response ping(){
        byte[] payload = ("Message from UserApplication | " + new Date()).getBytes();
        System.out.println("\n\n___\tMessange sent to kafka!\t___\n\n");
        ProducerRecord<String,byte[]> record = new ProducerRecord<>(
                properties.getProperty("kafka.topic.name"),
                payload
        );
        producer.send(record);
        return Response.ok().entity(payload).build();
    }
    // Commands

    @POST
    public Response addSingleAccToStorage(@Valid AccountDTO account) {
        AccountDTO accountDTO = accountAdapter.addAccount(account);
        return Response
                .ok()
               // .entity(accountDTO)
                .status(Response.Status.CREATED)
                .build();
    }

    @DELETE
    @Path("del/{str}")
    public Response removeSingleAccFromStorage(@PathParam("str") String str) {
        Optional<AccountDTO> acc = Optional.ofNullable(accountAdapter.getAccountViaUUID(str));
        if(acc.isPresent()){
            accountAdapter.removeAccount(acc.get());
            return Response
                    .ok()
                    .entity("Success")
                    .status(Response.Status.OK)
                    .build();
        }
        else{
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .entity("Wrong input data")
                    .build();
        }
    }

    @PUT
    @Path("update/{str}")
    public Response updateSingleAccount(@PathParam("str") String str, @Valid AccountDTO desiredAccount) {
        AccountDTO accountToChange = accountAdapter.getAccountViaUUID(str);
        // todo usprawnić logike update, tak aby pola null nie byly nullowane
        accountAdapter.updateSingleAccount(accountToChange, desiredAccount);
        return Response
                .ok()
                .entity("Account updated succesfully!")
                .status(Response.Status.OK)
                .build();
    }


    @Override
    public void close() throws Exception {
        producer.close();
    }
}
