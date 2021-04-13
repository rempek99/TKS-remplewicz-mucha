package pl.lodz.p.it.soap.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "firstName",
        "lastName",
        "roleOfUser",
        "isActive",
        "id",
        "login",
        "password"
})
public class PostAccountRequest {
    @XmlElement(required = true)
    private String firstName;
    private String lastName;
    private String roleOfUser;
    private boolean isActive;
    private String id;
    private String login;
    private String password;

    /**
     * Gets the value of the id property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the firstName property.
     *
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     *
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the lastName property.
     *
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     *
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the roleOfUser property.
     *
     */
    public String getRoleOfUser() {
        return roleOfUser;
    }

    /**
     * Sets the value of the roleOfUser property.
     *
     */
    public void setRoleOfUser(String value) {
        this.roleOfUser = value;
    }

    /**
     * Gets the value of the isActive property.
     *
     */
    public boolean getIsActive() {
        return isActive;
    }

    /**
     * Sets the value of the isActive property.
     *
     */
    public void setIsActive(boolean value) {
        this.isActive = value;
    }

    /**
     * Gets the value of the login property.
     *
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets the value of the login property.
     *
     */
    public void setLogin(String value) {
        this.login = value;
    }

    /**
     * Gets the value of the password property.
     *
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     *
     */
    public void setPassword(String value) {
        this.password = value;
    }
}
