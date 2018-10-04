package by.makhavenka.task.command.impl;

import by.makhavenka.task.command.Command;
import by.makhavenka.task.entity.Router;
import by.makhavenka.task.exception.ServiceException;
import by.makhavenka.task.service.impl.SupportServiceImpl;
import by.makhavenka.task.servlet.RequestController;
import by.makhavenka.task.exception.CommandException;
import by.makhavenka.task.service.SupportService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Сommand checks user and  changes to database.
 * Throws CommandException if invalid
 */
public class RegistrationCommand implements Command {

    private static final Logger lOGGER=LogManager.getLogger(RegistrationCommand.class);

    /**
     * The command creates SupportService and transfers content to service
     * uses method -- registration
     * @param content
     * @return router
     * @throws CommandException
     */
    @Override
    public Router execute(RequestController content) throws CommandException {
        SupportService service = new SupportServiceImpl();
        String result;
        Router router;
        try{
            result = service.registration(content);
        }catch (ServiceException e){
            throw new CommandException("error 'registrationCommand'",e);
        }

        lOGGER.info("The 'RegistrationCommand' executed successfully");
        router = new Router(result, Router.Type.REDIRECT);
        return router;

    }
}
