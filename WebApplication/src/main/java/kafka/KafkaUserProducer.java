package kafka;

import dtos.UserDTO;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaUserProducer implements AutoCloseable{

    private final KafkaProducer<String,UserDTO> producer;
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
        producer = new KafkaProducer<String, UserDTO>(properties);
    }

    public void sendCreateUserEvent(){
        UserDTO user = UserDTO
                .builder()
                .firstName("test")
                .lastName("testowy")
                .build();
        sendEvent(user);
    }

    private void sendEvent(UserDTO user){
//        String topic = properties.getProperty("kafka.topic.name");
        String key = "testkey";
        ProducerRecord<String,UserDTO> record = new ProducerRecord<>(
                properties.getProperty("kafka.topic.name"),
                key,
                user
        );
        try {
            producer.send(record).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        producer.close();
    }
}
