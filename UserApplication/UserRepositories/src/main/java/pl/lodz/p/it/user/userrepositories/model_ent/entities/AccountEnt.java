package pl.lodz.p.it.user.userrepositories.model_ent.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        AccountEnt that = (AccountEnt) o;

        return new EqualsBuilder()
                .append(isActive(), that.isActive())
                .append(getId(), that.getId())
                .append(getLogin(), that.getLogin())
                .append(getPassword(), that.getPassword())
                .append(getFirstName(), that.getFirstName())
                .append(getLastName(), that.getLastName())
                .append(getRoleOfUser(), that.getRoleOfUser())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getLogin())
                .append(getPassword())
                .append(getFirstName())
                .append(getLastName())
                .append(getRoleOfUser())
                .append(isActive())
                .toHashCode();
    }
}
