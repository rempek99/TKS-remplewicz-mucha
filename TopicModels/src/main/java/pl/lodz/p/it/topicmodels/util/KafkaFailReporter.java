package pl.lodz.p.it.topicmodels.util;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import pl.lodz.p.it.topicmodels.dtos.UserDTO;
import pl.lodz.p.it.topicmodels.events.FailureEvent;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

@Stateless
@LocalBean
public class KafkaFailReporter {

    private KafkaProducer<String, UserDTO> producer;
    private final Properties properties;

    public KafkaFailReporter() {
        // KafkaConfiguration
        properties = new Properties();
        properties.setProperty("bootstrap.servers","localhost:9092");
        properties.setProperty("kafka.topic.name", "UserResponseQueue");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "io.confluent.kafka.serializers.json.KafkaJsonSchemaSerializer");
        properties.put("schema.registry.url", "http://localhost:8081");

    }

    public void sendFailedCreadedUserEvent(UserDTO user){
        String key = FailureEvent.OPERATION_FAILED;
        sendEvent(key,user);
    }

    private void sendEvent(String key, UserDTO user){
        producer = new KafkaProducer<>(properties);
        ProducerRecord<String,UserDTO> record = new ProducerRecord<>(
                properties.getProperty("kafka.topic.name"),
                key,
                user
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
