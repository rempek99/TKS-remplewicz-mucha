package pl.lodz.p.it.rentapplicationapi.rest.movie;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import pl.lodz.p.it.rentapplicationapi.aggregates.adapters.MovieServiceAdapter;
import pl.lodz.p.it.rentapplicationapi.rentviewmodel.modelDTO.MovieDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.Optional;
import java.util.Properties;

@RequestScoped
@Path("movies")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class MovieAPI {

    @Inject
    MovieServiceAdapter movieAdapter;
    private final KafkaProducer<String,byte[]> producer;
    private final Properties properties;

    @Inject
    public MovieAPI() {
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
    public Response getMoviesFromStorage() {
        return Response
                .ok()
                .entity(movieAdapter.getAllMovies())
                .status(Response.Status.OK)
                .build();
    }

    // Commands

    @POST
    public Response addSingleMovieToStorage(MovieDTO movie) {
        MovieDTO movieDTO = movieAdapter.addMovie(movie);
        return Response
                .ok()
//                .entity(movieDTO)
                .status(Response.Status.CREATED)
                .build();
    }

    @DELETE
    @Path("del/{str}")
    public Response removeSingleMovieFromStorage(@PathParam("str") String str) {
        Optional<MovieDTO> movie = Optional.ofNullable(movieAdapter.getMovieViaUUID(str));
        if(movie.isPresent()){
            movieAdapter.removeMovie(movie.get());
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
    public Response updateSingleMovie(@PathParam("str") String str, MovieDTO desiredMovie) {
        MovieDTO movieToChange = movieAdapter.getMovieViaUUID(str);
        movieAdapter.updateSingleMovie(movieToChange, desiredMovie);
        return Response.ok().entity("Movie updated succesfully!").status(Response.Status.OK).build();
    }

}
