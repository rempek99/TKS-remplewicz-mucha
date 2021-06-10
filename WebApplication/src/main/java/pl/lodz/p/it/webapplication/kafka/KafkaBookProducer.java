package pl.lodz.p.it.webapplication.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import pl.lodz.p.it.topicmodels.dtos.BookDTO;
import pl.lodz.p.it.topicmodels.events.BookEvent;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaBookProducer {

    private  KafkaProducer<String, BookDTO> producer;
    private final Properties properties;

    public KafkaBookProducer() {
        // KafkaConfiguration
        properties = new Properties();
        properties.setProperty("bootstrap.servers","localhost:9092");
        properties.setProperty("kafka.topic.name", "BookTopic");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "io.confluent.kafka.serializers.json.KafkaJsonSchemaSerializer");
        properties.put("schema.registry.url", "http://localhost:8081");

    }

    public void sendCreateBookEvent(BookDTO book){
        String key = BookEvent.CREATE_BOOK;
        sendEvent(key,book);
    }

    private void sendEvent(String key, BookDTO book){
//        String topic = properties.getProperty("kafka.topic.name");

        producer = new KafkaProducer<String, BookDTO>(properties);
        ProducerRecord<String,BookDTO> record = new ProducerRecord<>(
                properties.getProperty("kafka.topic.name"),
                key,
                book
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
