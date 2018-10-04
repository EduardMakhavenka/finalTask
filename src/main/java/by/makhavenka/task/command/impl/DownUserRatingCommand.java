package by.makhavenka.task.command.impl;

import by.makhavenka.task.command.Command;
import by.makhavenka.task.entity.Router;
import by.makhavenka.task.exception.ServiceException;
import by.makhavenka.task.service.impl.UserServiceImpl;
import by.makhavenka.task.servlet.RequestController;
import by.makhavenka.task.exception.CommandException;
import by.makhavenka.task.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Сommand downs user rating and saves changes to database.
 * Throws CommandException if invalid
 */
public class DownUserRatingCommand implements Command {

    private static final Logger lOGGER=LogManager.getLogger(DownUserRatingCommand.class);

    /**
     * The command creates UserService and transfers content to service
     * uses method -- downUserRating
     * @param content
     * @return router
     * @throws CommandException
     */
    @Override
    public Router execute(RequestController content) throws CommandException {
       UserService service = new UserServiceImpl();
            String result;
            Router router;
            try{
                result= service.downUserRating(content);
            }catch (ServiceException e){
                throw  new CommandException("error 'downUserRatingCommand'",e);
            }

        lOGGER.info("The 'DownUserRatingCommand' executed successfully");
        router = new Router(result, Router.Type.REDIRECT);
        return router;
    }
}
