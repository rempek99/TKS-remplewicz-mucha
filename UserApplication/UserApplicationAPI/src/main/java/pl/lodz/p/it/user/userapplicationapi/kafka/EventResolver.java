package pl.lodz.p.it.user.userapplicationapi.kafka;

import pl.lodz.p.it.topicmodels.events.FailureEvent;
import pl.lodz.p.it.topicmodels.events.GeneralEvent;
import pl.lodz.p.it.topicmodels.events.UserEvent;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class EventResolver {

    public void resolve(GeneralEvent event) {
        if(event.getEventKey().equals(FailureEvent.OPERATION_FAILED))
        {}

    }
}
