package pl.lodz.p.it.webapplication.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import pl.lodz.p.it.topicmodels.dtos.MovieDTO;
import pl.lodz.p.it.topicmodels.events.MovieEvent;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaMovieProducer {

    private  KafkaProducer<String, MovieDTO> producer;
    private final Properties properties;

    public KafkaMovieProducer() {
        // KafkaConfiguration
        properties = new Properties();
        properties.setProperty("bootstrap.servers","localhost:9092");
        properties.setProperty("kafka.topic.name", "MovieTopic");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "io.confluent.kafka.serializers.json.KafkaJsonSchemaSerializer");
        properties.put("schema.registry.url", "http://localhost:8081");

    }

    public void sendCreateMovieEvent(MovieDTO movie){
        String key = MovieEvent.CREATE_MOVIE;
        sendEvent(key,movie);
    }

    private void sendEvent(String key, MovieDTO movie){
//        String topic = properties.getProperty("kafka.topic.name");

        producer = new KafkaProducer<String, MovieDTO>(properties);
        ProducerRecord<String,MovieDTO> record = new ProducerRecord<>(
                properties.getProperty("kafka.topic.name"),
                key,
                movie
        );
        try {
            producer.send(record).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }finally {
            producer.close();
        }
    }
}
