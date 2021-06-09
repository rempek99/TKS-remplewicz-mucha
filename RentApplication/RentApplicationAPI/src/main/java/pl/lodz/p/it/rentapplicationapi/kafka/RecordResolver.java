package pl.lodz.p.it.rentapplicationapi.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import pl.lodz.p.it.rentapplicationapi.aggregates.adapters.ClientServiceAdapter;
import pl.lodz.p.it.rentapplicationapi.aggregates.converters.ClientConverter;
import pl.lodz.p.it.rentrepositoriesadapters.model_ent.repositories.RepositoryException;
import pl.lodz.p.it.topicmodels.dtos.UserDTO;
import pl.lodz.p.it.topicmodels.events.UserEvent;
import pl.lodz.p.it.topicmodels.util.KafkaFailReporter;

import javax.inject.Inject;

public class RecordResolver {

    @Inject
    ClientServiceAdapter clientServiceAdapter;

    @Inject
    KafkaFailReporter failReporter;

    @Inject
    KafkaUserProducer userProducer;

    public void resolve(ConsumerRecord<String, UserDTO> record) {
        if (UserEvent.CREATE_USER.equals(record.key())) {
            try {
                clientServiceAdapter
                        .addClient(
                                ClientConverter.convertFromUserKafkaDTO(record.value()));
            }catch (IllegalArgumentException ie){
                if(ie.getCause().getMessage().equals(RepositoryException.DUPLICATED)){
                    failReporter.sendFailedCreadedUserEvent(record.value());
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                userProducer.sendRemoveUserEvent(record.value());
            }
        }

    }
}
