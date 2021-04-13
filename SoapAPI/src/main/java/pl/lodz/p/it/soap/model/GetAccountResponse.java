package pl.lodz.p.it.soap.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "account"
})
public class GetAccountResponse {
    @XmlElement(required = true)
    protected AccountSoap account;

    /**
     * Gets the value of the resource property.
     *
     * @return
     *     possible object is
     *     {@link AccountSoap }
     *
     */
    public AccountSoap getResource() {
        return account;
    }

    /**
     * Sets the value of the resource property.
     *
     * @param value
     *     allowed object is
     *     {@link AccountSoap}
     *
     */
    public void setResource(AccountSoap value) {
        this.account = value;
    }
}
