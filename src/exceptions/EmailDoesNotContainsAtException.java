package exceptions;

public class EmailDoesNotContainsAtException extends RuntimeException {
    public EmailDoesNotContainsAtException(String message) {
        super(message);
    }
}
