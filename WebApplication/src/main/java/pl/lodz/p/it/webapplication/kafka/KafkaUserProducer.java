package pl.lodz.p.it.webapplication.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import pl.lodz.p.it.topicmodels.dtos.UserDTO;
import pl.lodz.p.it.topicmodels.events.UserEvent;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaUserProducer{

    private  KafkaProducer<String, UserDTO> producer;
    private final Properties properties;

    public KafkaUserProducer() {
        // KafkaConfiguration
        properties = new Properties();
        properties.setProperty("bootstrap.servers","localhost:9092");
        properties.setProperty("kafka.topic.name", "UserTopic");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "io.confluent.kafka.serializers.json.KafkaJsonSchemaSerializer");
        properties.put("schema.registry.url", "http://localhost:8081");

    }

    public void sendCreateUserEvent(){
        String key = UserEvent.CREATE_USER;
        UserDTO user = UserDTO
                .builder()
                .firstName("test")
                .lastName("testowy")
                .build();
        sendEvent(key,user);
    }

    private void sendEvent(String key, UserDTO user){
//        String topic = properties.getProperty("kafka.topic.name");

        producer = new KafkaProducer<String, UserDTO>(properties);
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
