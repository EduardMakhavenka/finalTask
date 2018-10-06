package by.makhavenka.task.servlet;

import by.makhavenka.task.service.ServiceConstants;
import by.makhavenka.task.exception.ServiceException;
import by.makhavenka.task.service.DownloadeService;
import by.makhavenka.task.service.impl.DownlodeServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet(urlPatterns = "/uploadImage")
@MultipartConfig(fileSizeThreshold = 1024*1024,maxFileSize = 1024*1024*5,maxRequestSize = 1024*1024*5*5)
/**
 * class ImageDownloader downloades images
 */
public class ImageDownloader extends HttpServlet {
    private static final String UPLOAD_DIR = "img";
    private static final String ERROR_500 = "/jsp/errorpage/error500.jsp";


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try {

           String applicationPath = request.getServletContext().getRealPath("");
           String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
           File fileSaveDir = new File(uploadFilePath);
           if (!fileSaveDir.exists()) {
               fileSaveDir.mkdirs();
           }

           String nameImage;
           int count = 0;
           RequestController content = new RequestController(request);
           for (Part part : request.getParts()) {
               if(part.getSize()!=0) {
                   count++;
                   if(count==1){
                   int heroId = Integer.parseInt(content.getParameter(ServiceConstants.ID_HERO));
                   DownloadeService downloadeImage = new DownlodeServiceImpl();
                   nameImage = downloadeImage.downlodeHeroImage(heroId);
                   part.write(uploadFilePath + File.separator + nameImage.toLowerCase() + ".png");
                   }
               }
           }
           RequestDispatcher dispatcher = request.getRequestDispatcher(ServiceConstants.HOME);
           dispatcher.forward(request, response);
       }catch (ServiceException e){
           RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_500);
           dispatcher.forward(request, response);
       }
    }
}
