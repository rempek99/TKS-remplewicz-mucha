package pl.lodz.p.it.soap.api;

public class SoapException extends Exception {
    public static final String NOT_FOUND = "Not found";
    public SoapException(String message) {
        super(message);
    }
}
