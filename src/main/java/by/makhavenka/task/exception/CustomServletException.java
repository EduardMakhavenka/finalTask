package by.makhavenka.task.exception;

public class CustomServletException extends Exception {

    public CustomServletException(){}

    public CustomServletException(Throwable cause){
        super(cause);
    }

    public CustomServletException(String message){
        super(message);
    }

    public CustomServletException(String message, Throwable cause){
        super(message,cause);
    }
}
