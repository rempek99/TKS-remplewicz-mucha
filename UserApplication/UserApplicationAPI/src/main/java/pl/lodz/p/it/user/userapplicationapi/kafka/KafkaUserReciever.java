package pl.lodz.p.it.user.userapplicationapi.kafka;


import io.confluent.kafka.serializers.KafkaJsonDeserializerConfig;
import jdk.jfr.consumer.RecordedClass;
import org.apache.kafka.clients.consumer.*;
import pl.lodz.p.it.topicmodels.dtos.UserDTO;
import pl.lodz.p.it.topicmodels.events.GeneralEvent;
import pl.lodz.p.it.topicmodels.events.UserEvent;
import pl.lodz.p.it.user.userapplicationapi.aggregates.adapters.AccountServiceAdapter;
import pl.lodz.p.it.user.userapplicationapi.aggregates.converters.AccountConverter;
import pl.lodz.p.it.user.userapplicationapi.modelDTO.AccountDTO;
import pl.lodz.p.it.user.userapplicationapi.rest.account.AccountAPI;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.*;

@Startup
@Singleton
public class KafkaUserReciever implements AutoCloseable {

    private Consumer<String, UserDTO> consumer;
    private Properties properties;

    @Inject
    RecordResolver recordResolver;

    @Inject
    EventResolver eventResolver;



    public KafkaUserReciever() {

    }

    @PostConstruct
    private void init() {
        properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "group1");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "io.confluent.kafka.serializers.json.KafkaJsonSchemaDeserializer");
        properties.put("schema.registry.url", "http://localhost:8081");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(KafkaJsonDeserializerConfig.JSON_VALUE_TYPE, UserDTO.class.getName());

        String topic = "UserTopic";
        consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Arrays.asList(topic));

        Thread listenerThread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        ConsumerRecords<String, UserDTO> records = consumer.poll(100);
                        for (ConsumerRecord<String, UserDTO> record : records) {
                            GeneralEvent event = recordResolver.resolve(record);
                        }
                    }
                    catch (IllegalStateException e){
                        if (e.getMessage() != "This web container has not yet been started")
                        {}
//                            System.out.println("glupi blad");
                    }
                    catch(IllegalArgumentException e){
                        e.printStackTrace();
                    }
                    catch (Exception e) {
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