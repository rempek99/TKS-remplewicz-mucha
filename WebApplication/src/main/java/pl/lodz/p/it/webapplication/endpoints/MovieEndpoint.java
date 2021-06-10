package pl.lodz.p.it.webapplication.endpoints;

import pl.lodz.p.it.topicmodels.dtos.MovieDTO;
import pl.lodz.p.it.webapplication.kafka.KafkaMovieProducer;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@RequestScoped
@Path("movie")
public class MovieEndpoint {

    private final KafkaMovieProducer movieProducer = new KafkaMovieProducer();

    @POST
    @Consumes(APPLICATION_JSON)
    public void createMovie(MovieDTO movie){
        movieProducer.sendCreateMovieEvent(movie);
    }
}
