package by.makhavenka.task.service.impl;

import by.makhavenka.task.service.ServiceConstants;
import by.makhavenka.task.dao.Dao;
import by.makhavenka.task.dao.impl.CommentDaoImpl;
import by.makhavenka.task.dao.impl.UserDaoImpl;
import by.makhavenka.task.entity.AccessLevel;
import by.makhavenka.task.entity.User;
import by.makhavenka.task.exception.DAOException;
import by.makhavenka.task.exception.ServiceException;
import by.makhavenka.task.language.ConfigLocale;
import by.makhavenka.task.service.SupportService;
import by.makhavenka.task.servlet.RequestController;
import by.makhavenka.task.validator.*;

import java.util.*;

public class SupportServiceImpl implements SupportService {

    /**
     * method sets and changes locale
     * @param content
     * @return String
     * @throws ServiceException
     */
    @Override
    public String locale(RequestController content) throws ServiceException {
        content.setSessionAttribute(ServiceConstants.LOGIN_ERROR,null);
        String choosenLocale=content.getParameter(ServiceConstants.LANG_PARAMETER);
        content.setSessionAttribute(ServiceConstants.LANGUAGE,choosenLocale);
        return ServiceConstants.LOGIN_JSP;
    }



    /**
     * method registers user to database
     * use UserDaoImpl. method:findAll()
     * @param content
     * @return String
     * @throws ServiceException
     */
    @Override
    public String registration(RequestController content) throws ServiceException {
        boolean resultOfValidation=true;
        String login = content.getParameter(ServiceConstants.REGISTER_LOGIN);
        if(!LoginValidator.validate(login)){
            resultOfValidation= false;
        }
        String name = content.getParameter(ServiceConstants.REGISTER_NAME);
        if(!NameUserValidator.validate(name)){
            resultOfValidation= false;
        }
        String surname = content.getParameter(ServiceConstants.REGISTER_SURNAME);
        if(!SurnameValidator.validate(surname)){
            resultOfValidation= false;
        }
        String nickname = content.getParameter(ServiceConstants.REGISTER_NICKNAME);
        if(!NicknameValidator.validate(nickname)){
            resultOfValidation= false;
        }
        String mail = content.getParameter(ServiceConstants.REGISTER_EMAIL);
        if(!EmailValidator.validate(mail)){
            resultOfValidation= false;
        }
        String password =content.getParameter(ServiceConstants.REGISTER_PASSWORD);
        if(!PasswordValidator.validate(password)){
            resultOfValidation = false;
        }
        if(!resultOfValidation){
            return ServiceConstants.REGISTER_JSP;
        }

        Dao userDao = new UserDaoImpl();
        boolean testResult = true;
        try {
            List<User> usersList;
            usersList = userDao.findAll();
            for (User user:usersList){
                if(login.equals(user.getLogin())){
                    testResult = false;
                }
            }
        } catch (DAOException e) {
            throw new ServiceException("check login registration error",e);
        }

        if(testResult){
            User user = new User();
            user.setName(content.getParameter(ServiceConstants.REGISTER_NAME));
            user.setSurname(content.getParameter(ServiceConstants.REGISTER_SURNAME));
            user.setLogin(content.getParameter(ServiceConstants.REGISTER_LOGIN));
            user.setNickName(content.getParameter(ServiceConstants.REGISTER_NICKNAME));
            user.setMail(content.getParameter(ServiceConstants.REGISTER_EMAIL));
            user.setPassword(content.getParameter(ServiceConstants.REGISTER_PASSWORD));
            user.setBanned(0);
            user.setRating(1000);
            user.setRole(AccessLevel.USER.toString());

            User activeUser;
            try {
                userDao.add(user);
                activeUser = ((UserDaoImpl) userDao).findByLogin(user.getLogin());
                content.setSessionAttribute(ServiceConstants.MAIN_ID,activeUser.getId());
                content.setSessionAttribute(ServiceConstants.USER_NICKNAME,activeUser.getNickName());
                return ServiceConstants.HOME;
            } catch (DAOException e) {
                throw new ServiceException("add register new user error",e);
            }
        }else {
            ConfigLocale configLocale= new ConfigLocale((String)content.getSessionAttribute(ServiceConstants.LANGUAGE));
            content.setSessionAttribute(ServiceConstants.ATTRIBUTE_NAME,configLocale.getConfigLocale(ServiceConstants.REGISTER_ERROR));
            return ServiceConstants.REGISTER_JSP;
        }
    }


    /**
     * Check out user and invalidate session
     * @param content
     * @return String
     * @throws ServiceException
     */
    @Override
    public String logout(RequestController content) throws ServiceException {
        content.setSessionInvalidateFlag(true);
        return ServiceConstants.HOME;
    }

    /**
     * show user profile
     * use UserDaoImpl() and CommentDaoImpl(), method:findAllById, findById
     * @param content
     * @return String
     * @throws ServiceException
     */
    @Override
    public String showProfile(RequestController content)throws ServiceException {

        int id=(int)content.getSessionAttribute(ServiceConstants.MAIN_ID);
        Dao userDao = new UserDaoImpl();
        Dao commentDao = new CommentDaoImpl();
        User user;
        try {
            user =((UserDaoImpl)userDao).findById(id);

            content.setSessionAttribute(ServiceConstants.USER_ID,user.getId());
            content.setSessionAttribute(ServiceConstants.NICKNAME,user.getNickName());
            content.setSessionAttribute(ServiceConstants.USERS_NAME,user.getName());
            content.setSessionAttribute(ServiceConstants.SURNAME,user.getSurname());
            content.setSessionAttribute(ServiceConstants.AGE,user.getAge());
            content.setSessionAttribute(ServiceConstants.MAIL,user.getMail());
            content.setSessionAttribute(ServiceConstants.USERS_RATING,user.getRating());
            content.setSessionAttribute(ServiceConstants.ROLE,user.getRole());
            content.setSessionAttribute(ServiceConstants.PHOTO,user.getPhoto());
            content.setSessionAttribute(ServiceConstants.FAVORITE_HERO,user.getFavotiteHero());
            content.setSessionAttribute(ServiceConstants.SEX,user.getSex());
            content.setSessionAttribute(ServiceConstants.PHONE,user.getPhone());
        } catch (DAOException e) {
            throw new ServiceException("show profile service error1",e);
        }

        Map<String,String> comments;
        try {
            comments=((CommentDaoImpl) commentDao).findAllById(id);
            content.setSessionAttribute(ServiceConstants.COMMENTS,comments);
        } catch (DAOException e) {
            throw new ServiceException("show profile service error2",e);
        }
        return ServiceConstants.PROFILE_JSP;
    }
}

