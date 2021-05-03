package pl.lodz.p.it.user.userapplicationapi.exceptions;

public class RestException extends Exception{
    public static final String NOT_FOUND = "Not Found";

    public RestException(String message) {
        super(message);
    }
}
