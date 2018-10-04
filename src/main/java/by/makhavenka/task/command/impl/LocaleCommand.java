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
 * Сommand changes locale .
 * Throws CommandException if invalid
 */
public class LocaleCommand implements Command {

    private static final Logger lOGGER=LogManager.getLogger(LocaleCommand.class);

    /**
     * The command creates SupportService and transfers content to service
     * uses method -- locale
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
           result = service.locale(content);
        }catch (ServiceException e){
            throw new CommandException("error 'localeCommand'",e);
        }

        lOGGER.info("The 'LocaleCommand' executed successfully");
        router = new Router(result, Router.Type.FORWARD);
        return router;
    }
}
