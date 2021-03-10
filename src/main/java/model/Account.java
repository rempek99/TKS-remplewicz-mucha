package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;
import java.util.UUID;

public class Account {

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

    public Account() {
        this.id = UUID.randomUUID().toString();
    }

    @JsonCreator
    public Account(@JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName, @JsonProperty("roleOfUser") String roleOfUser, @JsonProperty("active") boolean isActive, @JsonProperty("login") String login, @JsonProperty("password") String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleOfUser = roleOfUser;
        this.isActive = isActive;
        this.login = login;
        this.password = password;
        this.id = UUID.randomUUID().toString();
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRoleOfUser() {
        return roleOfUser;
    }

    public void setRoleOfUser(String roleOfUser) {
            this.roleOfUser = roleOfUser;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
            isActive = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", roleOfUser='" + roleOfUser + '\'' +
                ", isActive=" + isActive +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return isActive() == account.isActive() &&
                getFirstName().equals(account.getFirstName()) &&
                getLastName().equals(account.getLastName()) &&
                getRoleOfUser().equals(account.getRoleOfUser()) &&
                Objects.equals(getId(), account.getId()) &&
                getLogin().equals(account.getLogin()) &&
                getPassword().equals(account.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getRoleOfUser(), isActive(), getId(), getLogin(), getPassword());
    }
}
