package by.makhavenka.task.command;


import by.makhavenka.task.exception.CommandException;
import by.makhavenka.task.servlet.RequestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandFactory {

    public static final Logger LOGGER=LogManager.getLogger(CommandFactory.class);
    private static final String COMMAND_PARAM="command";

    /**
     * Returns a command from the command enum
     * corresponding to the parameter 'command'
     * from the content.
     *
     * @param content
     * @return Command
     * @throws CommandException
     */
    public static Command defineCommand(RequestController content) throws CommandException {
        Command command = null;
        String actionCommand = content.getParameter(COMMAND_PARAM);
        if (actionCommand==null) {
            throw  new CommandException("Command null error");
        }

        try {
            TypeCommand commandEnum=TypeCommand.valueOf(actionCommand.toUpperCase());
            command=commandEnum.getCommand();
        }catch (IllegalArgumentException e){
            LOGGER.error("Illegal argument "+e);

        }

      return command;
    }

}
