package pl.lodz.p.it.soap.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class SoapException extends Exception {
    public static final String NOT_FOUND = "Not found";
    public static final String DUPLICATED = "Duplicated";

    public SoapException(String message) {
        super(message);
    }
}
