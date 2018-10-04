package by.makhavenka.task.command;

import by.makhavenka.task.entity.Router;
import by.makhavenka.task.servlet.RequestController;
import by.makhavenka.task.exception.CommandException;

/**
 * Applications command interface
 */
public interface Command {
    /**
     *
     * @param content
     * @return Router
     * @throws CommandException
     */
    Router execute(RequestController content) throws CommandException;
}
