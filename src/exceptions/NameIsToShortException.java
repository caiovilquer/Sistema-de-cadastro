package exceptions;

public class NameIsToShortException extends RuntimeException {
    public NameIsToShortException(String message) {
        super(message);
    }
}
