package by.makhavenka.task.exception;

/**
 * Handles dao layer excepions
 */
public class DAOException extends Exception {

    public DAOException(){}

    public DAOException(Throwable cause){
        super(cause);
    }

    public DAOException(String message){
        super(message);
    }

    public DAOException(String message, Throwable cause){
        super(message,cause);
    }

}
