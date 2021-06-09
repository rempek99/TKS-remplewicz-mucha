package pl.lodz.p.it.topicmodels.events;

import lombok.Data;

@Data
public class GeneralEvent {
    private String eventKey;

    public GeneralEvent(String eventKey) {
        this.eventKey = eventKey;
    }
}
