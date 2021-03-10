package model.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class AccountEnt {

    @NotNull(message = "Please enter your first name")
    @Pattern(regexp = "[a-zA-Z]+")
    private String firstName;
    @NotNull(message = "Please enter your last name")
    @Pattern(regexp = "[a-zA-Z]+")
    private String lastName;
    @NotNull(message = "Please choose one from the list")
    private String roleOfUser;
    private boolean isActive;
    @NotNull
    private String id;
    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9]+")
    private String login;
    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9]+")
    private String password;

    public AccountEnt() {
        this.id = UUID.randomUUID().toString();
    }

    @JsonCreator
    public AccountEnt(@JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName, @JsonProperty("roleOfUser") String roleOfUser, @JsonProperty("active") boolean isActive, @JsonProperty("login") String login, @JsonProperty("password") String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleOfUser = roleOfUser;
        this.isActive = isActive;
        this.login = login;
        this.password = password;
        this.id = UUID.randomUUID().toString();
    }
}
