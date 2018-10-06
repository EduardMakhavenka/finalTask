package by.makhavenka.task.servlet;

import by.makhavenka.task.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

public class RequestController {

    private static final String LANGUAGE="language";

    private HashMap<String, Object> requestAttributes = new HashMap<>();
    private HashMap<String, String[]> requestParameters = new HashMap<>();
    private HashMap<String, Object> sessionAttributes = new HashMap<>();
    private String requestURI;
    private boolean sessionInvalidateFlag;


    public RequestController(HttpServletRequest request) {
        pasreRequestAttibutes(request);
        parseRequestParameters(request);
        parseSessionAttributes(request);

        requestURI=request.getRequestURI();
        sessionInvalidateFlag=false;
    }

    private void pasreRequestAttibutes(HttpServletRequest request){
        Enumeration<String> attributes=request.getAttributeNames();

        while (attributes.hasMoreElements()){
            String attributeName = attributes.nextElement();
            requestAttributes.put(attributeName,request.getAttribute(attributeName));
        }

    }

    private void parseRequestParameters(HttpServletRequest request){
        Enumeration<String> parameters=request.getParameterNames();

        while (parameters.hasMoreElements()){
            String parameterName=parameters.nextElement();
            requestParameters.put(parameterName,request.getParameterValues(parameterName));
        }
    }

    private void parseSessionAttributes(HttpServletRequest request){
        HttpSession session=request.getSession(false);

        if(session!=null) {
            Enumeration<String> sessionAttributes = session.getAttributeNames();

            while (sessionAttributes.hasMoreElements()) {
                String sessionAttributeName = sessionAttributes.nextElement();
                this.sessionAttributes.put(sessionAttributeName, session.getAttribute(sessionAttributeName));
            }
        }
    }

    public void setSessionAttribute(String attributeName,Object value){
        sessionAttributes.put(attributeName,value);
    }

    public HashMap<String, Object> getRequestAttributes() {
        return requestAttributes;
    }

    public void setSessionInvalidateFlag(boolean sessionInvalidateFlag){
        this.sessionInvalidateFlag=sessionInvalidateFlag;
    }


    public String getParameter(String key)  {

            return  requestParameters.get(key)[0];

    }

    public Object getSessionAttribute(String attributeName){
        return sessionAttributes.get(attributeName);
    }

    public Object setAttribute(String key,Object value){
        return requestAttributes.put(key,value);
    }

    void insertValues(HttpServletRequest request){
        Iterator iterator=requestAttributes.entrySet().iterator();

        while(iterator.hasNext()){
            Map.Entry<String,Object> pair=(Map.Entry)iterator.next();
            request.setAttribute(pair.getKey(),pair.getValue());
        }

        iterator=sessionAttributes.entrySet().iterator();
        HttpSession session=request.getSession(false);

        if(session!=null){

            while (iterator.hasNext()){
                Map.Entry<String,Object> pair =(Map.Entry)iterator.next();
                session.setAttribute(pair.getKey(),pair.getValue());
            }

            if(sessionInvalidateFlag){
                String currentLocale = (String)request.getSession().getAttribute(LANGUAGE);
                session.invalidate();
                request.getSession().setAttribute(LANGUAGE,currentLocale);
            }
        }
    }
}
