package by.makhavenka.task.exception;

public class FilterException extends Exception {

    public FilterException() {}

    public FilterException(Throwable cause) { super(cause); }

    public FilterException(String message) {
        super(message);
    }

    public FilterException(String message, Throwable cause) {
        super(message, cause);
    }
}
