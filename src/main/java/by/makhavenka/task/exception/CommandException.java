package by.makhavenka.task.exception;

/**
 * Handles command layer excepions
 */
public class CommandException extends Exception {
    public CommandException(){}

    public CommandException(Throwable cause){ super(cause); }

    public CommandException(String message){
        super(message);
    }

    public CommandException(String message,Throwable cause){
        super(message,cause);
    }
}
