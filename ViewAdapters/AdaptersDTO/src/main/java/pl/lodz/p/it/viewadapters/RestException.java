package pl.lodz.p.it.viewadapters;

public class RestException extends Exception{
    public static final String NOT_FOUND = "Not Found";

    public RestException(String message) {
        super(message);
    }
}
