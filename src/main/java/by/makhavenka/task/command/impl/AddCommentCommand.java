package by.makhavenka.task.command.impl;

import by.makhavenka.task.command.Command;
import by.makhavenka.task.entity.Router;
import by.makhavenka.task.exception.ServiceException;
import by.makhavenka.task.poolconnection.Pool;
import by.makhavenka.task.service.impl.ContentServiceImpl;
import by.makhavenka.task.servlet.RequestController;
import by.makhavenka.task.exception.CommandException;
import by.makhavenka.task.service.ContentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *Сommand to add user comments to the database.
 * Throws CommandException if invalid.
 */
public class AddCommentCommand implements Command {

    private static final Logger lOGGER=LogManager.getLogger(AddCommentCommand.class);

    /**
     * Create ContentServiceImpl.
     * sends content to the service
     * uses method -- addComment
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
            result= service.addComment(content);
        }catch (ServiceException e){
            throw new CommandException(" error 'addCommentCommand' ",e);
        }

        lOGGER.info("The 'AddCommentCommand' executed successfully");
        router = new Router(result, Router.Type.REDIRECT);
        return router;
    }
}
