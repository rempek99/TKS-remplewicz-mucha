package pl.lodz.p.it.topicmodels.events;

import pl.lodz.p.it.topicmodels.dtos.UserDTO;

public class UserEvent extends GeneralEvent {
    public static final String CREATE_USER = "CreateUser";
    public static final String REMOVE_USER = "RemoveUser";

    private UserDTO object;

    public UserEvent(String eventKey, UserDTO object) {
        super(eventKey);
        this.object = object;
    }

    public static UserEvent createUserEvent(UserDTO value) {
        return new UserEvent(CREATE_USER, value);
    }
}
