package pl.lodz.p.it.soap.model;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "accountSoap", propOrder = {
        "firstName",
        "lastName",
        "roleOfUser",
        "isActive",
        "id",
        "login",
        "password"
})
public class AccountSoap {
    private String firstName;
    private String lastName;
    private String roleOfUser;
    private boolean isActive;
    private String id;
    private String login;
    private String password;

    public AccountSoap() {
        this.id = UUID.randomUUID().toString();
    }

    public AccountSoap(String firstName, String lastName, String roleOfUser, boolean isActive, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleOfUser = roleOfUser;
        this.isActive = isActive;
        this.login = login;
        this.password = password;
        this.id = UUID.randomUUID().toString();
    }
}