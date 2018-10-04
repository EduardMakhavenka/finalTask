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
 * The command deletes a hero from site .
 * and saves changes to database.
 * Throws CommandException if invalid
 */
public class DeleteHeroCommand implements Command{

        private static final Logger lOGGER=LogManager.getLogger(DeleteHeroCommand.class);

    /**
     * The command creates AdminService and transfers content to service
     * uses method -- deleteHero
     * @param content
     * @return router
     * @throws CommandException
     */
    @Override
    public Router execute(RequestController content) throws CommandException {
        AdminService service= new AdminServiceImpl();
        String result;
        Router router;
        try{
            result = service.deleteHero(content);
        }catch (ServiceException e){
            throw new CommandException("error 'deleteHeroCommand'",e);
        }

        lOGGER.info("The 'DeleteHeroCommand' executed successfully");
        router = new Router(result, Router.Type.FORWARD);
        return router;
    }
}
