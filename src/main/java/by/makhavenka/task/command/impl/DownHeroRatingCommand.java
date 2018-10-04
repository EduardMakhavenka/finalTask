package by.makhavenka.task.command.impl;

import by.makhavenka.task.command.Command;
import by.makhavenka.task.entity.Router;
import by.makhavenka.task.exception.ServiceException;
import by.makhavenka.task.service.impl.ContentServiceImpl;
import by.makhavenka.task.servlet.RequestController;
import by.makhavenka.task.exception.CommandException;
import by.makhavenka.task.service.ContentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Сommand downs hero rating and saves changes to database.
 * Throws CommandException if invalid
 */
public class DownHeroRatingCommand implements Command {

    private static final Logger lOGGER=LogManager.getLogger(DownHeroRatingCommand.class);

    /**
     * The command creates ContentService and transfers content to service
     * uses method -- downHeroRating
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
            result = service.downHeroRating(content);
        }catch (ServiceException e){
            throw new CommandException("error 'downHeroRatingCommand'",e);
        }

        lOGGER.info("The 'DownHeroRatingCommand' executed successfully");
        router = new Router(result, Router.Type.REDIRECT);
        return  router;
    }
}
