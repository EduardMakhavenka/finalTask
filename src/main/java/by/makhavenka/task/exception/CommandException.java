package by.makhavenka.task.exception;

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
