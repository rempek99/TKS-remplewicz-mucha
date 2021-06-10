package pl.lodz.p.it.rentapplicationapi.kafka;

import io.confluent.kafka.serializers.KafkaJsonDeserializerConfig;
import org.apache.kafka.clients.consumer.*;
import pl.lodz.p.it.topicmodels.dtos.BookDTO;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

@Startup
@Singleton
public class KafkaBookReciever implements AutoCloseable{

    private Consumer<String, BookDTO> consumer;
    private Properties properties;

    @Inject
    RecordResolver recordResolver;


    public KafkaBookReciever() {

    }

    @PostConstruct
    private void init() {
        properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "group2");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "io.confluent.kafka.serializers.json.KafkaJsonSchemaDeserializer");
        properties.put("schema.registry.url", "http://localhost:8081");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(KafkaJsonDeserializerConfig.JSON_VALUE_TYPE, BookDTO.class.getName());

        String topic = "BookTopic";
        consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Arrays.asList(topic));

        Thread listenerThread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        ConsumerRecords<String, BookDTO> records = consumer.poll(10);
                        for (ConsumerRecord<String, BookDTO> record : records) {
                            recordResolver.resolveBook(record);
                        }
                    } catch (IllegalStateException e) {
                        if (e.getMessage() != "This web container has not yet been started") {
                            System.out.println(e.getMessage());
                        }
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        };
        TimerTask task = new TimerTask() {
            public void run() {
                listenerThread.start();
            }
        };
        Timer timer = new Timer("Timer");
        long delay = 2000L;
        timer.schedule(task, delay);
    }


    @Override
    public void close() throws Exception {
        consumer.close();
    }
}
