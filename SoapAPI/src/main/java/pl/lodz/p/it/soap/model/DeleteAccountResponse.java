package pl.lodz.p.it.soap.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "info"
})
public class DeleteAccountResponse {
    @XmlElement(required = true)
    protected String info;

    /**
     * Gets the value of the info property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getInfo() {
        return info;
    }

    /**
     * Sets the value of the info property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setInfo(String value) {
        this.info = value;
    }

}
