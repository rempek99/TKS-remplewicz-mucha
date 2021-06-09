package pl.lodz.p.it.topicmodels.events;

public class FailureEvent extends GeneralEvent{

    public static final String OPERATION_FAILED = "Operation Failed";
    public static final String REMOVE_USER = "Remove User";
    private String causeKey;

    public FailureEvent(String eventKey, String causeKey) {
        super(eventKey);
        this.causeKey = causeKey;
    }
    public static FailureEvent createFailureEvent(String causeKey){
        return new FailureEvent(OPERATION_FAILED, causeKey);
    }
}
