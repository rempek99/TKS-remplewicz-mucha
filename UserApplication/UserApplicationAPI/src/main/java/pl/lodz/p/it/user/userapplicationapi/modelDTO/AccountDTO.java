package pl.lodz.p.it.user.userapplicationapi.modelDTO;

import lombok.*;

import java.util.UUID;

@Data
public class AccountDTO {
    private String firstName;
    private String lastName;
    private String roleOfUser;
    private boolean isActive;
    private String id;
    private String login;
    private String password;

    public AccountDTO() {
        this.id = UUID.randomUUID().toString();
    }

    public AccountDTO(String firstName, String lastName, String roleOfUser, boolean isActive, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleOfUser = roleOfUser;
        this.isActive = isActive;
        this.login = login;
        this.password = password;
        this.id = UUID.randomUUID().toString();
    }
}