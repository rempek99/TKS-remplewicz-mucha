package model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Account {
    private String firstName;
    private String lastName;
    private String roleOfUser;
    private boolean isActive;
    private String id;
    private String login;
    private String password;

    public Account() {
        this.id = UUID.randomUUID().toString();
    }

    public Account(String firstName, String lastName, String roleOfUser, boolean isActive, String login,  String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleOfUser = roleOfUser;
        this.isActive = isActive;
        this.login = login;
        this.password = password;
        this.id = UUID.randomUUID().toString();
    }
}