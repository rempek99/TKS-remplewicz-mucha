package model_ent.entities;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class AccountEnt {

    private String id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String roleOfUser;
    private boolean isActive;

    public AccountEnt(String firstName, String lastName, String roleOfUser, boolean isActive, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleOfUser = roleOfUser;
        this.isActive = isActive;
        this.login = login;
        this.password = password;
    }
}
