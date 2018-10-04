package by.makhavenka.task.command.impl;

import by.makhavenka.task.command.Command;
import by.makhavenka.task.entity.Router;
import by.makhavenka.task.exception.ServiceException;
import by.makhavenka.task.service.impl.AdminServiceImpl;
import by.makhavenka.task.servlet.RequestController;
import by.makhavenka.task.exception.CommandException;
import by.makhavenka.task.service.AdminService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The command deletes a comment from site .
 * and saves changes to database.
 * Throws CommandException if invalid
 */
public class DeleteCommentCommand implements Command {

    private static final Logger lOGGER=LogManager.getLogger(DeleteCommentCommand.class);

    /**
     * The command creates AdminService and transfers content to service
     * uses method -- deleteComment
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
            result =  service.deleteComment(content);
        }catch (ServiceException e){
            throw new CommandException("error 'deleteCommentCommand'",e);
        }

        lOGGER.info("The 'DeleteCommentCommand' executed successfully");
        router = new Router(result, Router.Type.FORWARD);
        return router;
    }
}
