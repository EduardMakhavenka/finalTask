package by.makhavenka.task.command.impl;

import by.makhavenka.task.command.Command;
import by.makhavenka.task.entity.Router;
import by.makhavenka.task.exception.CommandException;
import by.makhavenka.task.exception.ServiceException;
import by.makhavenka.task.service.AdminService;
import by.makhavenka.task.service.impl.AdminServiceImpl;
import by.makhavenka.task.servlet.RequestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Сommand  shows deleted comments.
 * Throws CommandException if invalid
 */
public class ShowDeletedCommentsCommand implements Command {

    private static final Logger lOGGER=LogManager.getLogger(ShowDeletedCommentsCommand.class);

    /**
     * The command creates AdminService and transfers content to service
     * uses method -- showDeletedComments
     * @param content
     * @return router
     * @throws CommandException
     */
    @Override
    public Router execute(RequestController content) throws CommandException {
        AdminService service = new AdminServiceImpl();
        String result;
        Router router;
        try{
            result=service.showDeletedComments(content);
        }catch (ServiceException e){
            throw new CommandException("error 'showDeletedComments'",e);
        }

        lOGGER.info("The 'ShowDeletedCommentsCommand' executed successfully");
        router = new Router(result, Router.Type.FORWARD);
        return  router;
    }
}
