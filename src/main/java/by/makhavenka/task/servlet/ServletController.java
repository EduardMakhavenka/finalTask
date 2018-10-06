package by.makhavenka.task.servlet;

import by.makhavenka.task.command.Command;
import by.makhavenka.task.command.CommandFactory;
import by.makhavenka.task.entity.Router;
import by.makhavenka.task.exception.CommandException;
import by.makhavenka.task.poolconnection.Pool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The class receives and invokes approprite commands
 */
public class ServletController extends HttpServlet {

    private static final Logger LOGGER=LogManager.getLogger(ServletController.class);
    private static final String ERROR_404 = "/jsp/errorpage/error404.jsp";
    private static final String ERROR_500 = "/jsp/errorpage/error500.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        processRequest(request,response);
    }

    @Override
    public void destroy(){
        Pool.getInstance().clearConnectionQueues();
        super.destroy();
    }

    private void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{

        RequestController content = new RequestController(request);

        Command command = null;
        try {
            command = CommandFactory.defineCommand(content);
        } catch (CommandException e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_404);
            dispatcher.forward(request,response);
            LOGGER.error("error try to defineCommand command",e);
        }
        Router page;
        try{
            page=command.execute(content);
            content.insertValues(request);
            if(Router.Type.FORWARD.equals(page.getType())){
                RequestDispatcher dispatcher = request.getRequestDispatcher(page.getPage());
                dispatcher.forward(request,response);
            }else {
                response.sendRedirect(page.getPage());
            }
        }catch (CommandException e){
            LOGGER.error("error try to execute command",e);
            RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_500);
            dispatcher.forward(request,response);
        }

    }
}
