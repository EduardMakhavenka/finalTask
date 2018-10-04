package by.makhavenka.task.command.impl;

import by.makhavenka.task.command.Command;
import by.makhavenka.task.entity.Router;
import by.makhavenka.task.service.impl.UserServiceImpl;
import by.makhavenka.task.servlet.RequestController;
import by.makhavenka.task.exception.CommandException;
import by.makhavenka.task.exception.ServiceException;
import by.makhavenka.task.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Сommand authorizes user and  changes to database.
 * Throws CommandException if invalid
 */
public class LoginCommand implements Command {

    private static final Logger lOGGER=LogManager.getLogger(LoginCommand.class);

    /**
     * The command creates UserService and transfers content to service
     * uses method -- login
     * @param content
     * @return router
     * @throws CommandException
     */
    @Override
    public Router execute(RequestController content) throws CommandException {
        String result;
        Router router;
        try {
            UserService service = new UserServiceImpl();
            result = service.login(content);
        } catch (ServiceException e) {
            throw new CommandException("Login user command error",e);
        }

        lOGGER.info("The 'LoginCommand' executed successfully");
        router = new Router(result, Router.Type.REDIRECT);
        return router;

    }
}
