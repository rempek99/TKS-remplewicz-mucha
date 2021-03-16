package model_ent.entities;

import lombok.*;

import java.util.Objects;

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
        if (!(o instanceof AccountEnt)) return false;
        AccountEnt account = (AccountEnt) o;
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
