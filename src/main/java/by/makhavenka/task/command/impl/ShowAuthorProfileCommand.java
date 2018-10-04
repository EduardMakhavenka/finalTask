package by.makhavenka.task.command.impl;

import by.makhavenka.task.command.Command;
import by.makhavenka.task.entity.Router;
import by.makhavenka.task.exception.CommandException;
import by.makhavenka.task.exception.ServiceException;
import by.makhavenka.task.service.ContentService;
import by.makhavenka.task.service.impl.ContentServiceImpl;
import by.makhavenka.task.servlet.RequestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Сommand  shows author's profile .
 * Throws CommandException if invalid
 */
public class ShowAuthorProfileCommand implements Command {

    private static final Logger lOGGER=LogManager.getLogger(ShowAuthorProfileCommand.class);

    /**
     * The command creates ContentService and transfers content to service
     * uses method -- showAuthorProfile
     * @param content
     * @return router
     * @throws CommandException
     */
    @Override
    public Router execute(RequestController content) throws CommandException {
        ContentService service= new ContentServiceImpl();
        String result;
        Router router;
        try{
            result = service.showAuthorProfile(content);
        }catch (ServiceException e){
            throw new CommandException("error 'showAuthorProfile Command'",e);
        }

        lOGGER.info("The 'ShowAuthorProfileCommand' executed successfully");
        router = new Router(result, Router.Type.FORWARD);
        return router;
    }
}
