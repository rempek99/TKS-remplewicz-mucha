package pl.lodz.p.it.user.userapplicationapi.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import pl.lodz.p.it.topicmodels.events.FailureEvent;
import pl.lodz.p.it.topicmodels.dtos.UserDTO;
import pl.lodz.p.it.topicmodels.events.GeneralEvent;
import pl.lodz.p.it.topicmodels.events.UserEvent;
import pl.lodz.p.it.topicmodels.util.KafkaFailReporter;
import pl.lodz.p.it.user.userapplicationapi.aggregates.adapters.AccountServiceAdapter;
import pl.lodz.p.it.user.userapplicationapi.aggregates.converters.AccountConverter;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@LocalBean
public class RecordResolver {

    @Inject
    private AccountServiceAdapter accountAdapter;

    @Inject
    private KafkaFailReporter failReporter;

    public void resolve(ConsumerRecord<String, UserDTO> record) {
        if (UserEvent.CREATE_USER.equals(record.key())) {
            try {
                accountAdapter
                        .addAccount(
                                AccountConverter.convertFromUserKafkaDTO(record.value()));

            } catch (Exception e) {
                e.printStackTrace();
                failReporter.sendFailedCreadedUserEvent(record.value());
            }
        }
        if(UserEvent.REMOVE_USER.equals(record.key())){
            try {
                accountAdapter
                        .removeAccount(
                                AccountConverter.convertFromUserKafkaDTO(record.value()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
