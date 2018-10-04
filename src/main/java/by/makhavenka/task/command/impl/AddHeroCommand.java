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
 * Сommand adds character to the database
 * Throws CommandException if invalid
 */
public class AddHeroCommand implements Command {
    private static final Logger lOGGER=LogManager.getLogger(AddHeroCommand.class);

    /**
     * The command creates AdminService and transfer content to the service.
     * uses method -- addHero
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
            result=service.addHero(content);
        }catch (ServiceException e){
            throw new CommandException("error 'addHeroCommand'",e);
        }

        lOGGER.info("The 'AddHeroCommand' executed successfully");
        router = new Router(result, Router.Type.REDIRECT);
        return  router;
    }
}
