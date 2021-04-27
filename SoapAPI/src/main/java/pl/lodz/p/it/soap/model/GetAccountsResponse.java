package pl.lodz.p.it.soap.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "account"
})
public class GetAccountsResponse {
    @XmlElement(nillable = true)
    protected List<AccountSoap> account;

    /**
     * Gets the value of the pl.lodz.p.it.rentviewports.account property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pl.lodz.p.it.rentviewports.account property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccount().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccountSoap }
     *
     *
     */
    public List<AccountSoap> getAccount() {
        if (account == null) {
            account = new ArrayList<AccountSoap>();
        }
        return this.account;
    }

}
