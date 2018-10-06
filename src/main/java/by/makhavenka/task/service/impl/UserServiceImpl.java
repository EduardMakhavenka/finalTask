package by.makhavenka.task.service.impl;

import by.makhavenka.task.service.ServiceConstants;
import by.makhavenka.task.dao.Dao;
import by.makhavenka.task.dao.impl.UserDaoImpl;
import by.makhavenka.task.entity.User;
import by.makhavenka.task.service.UserService;
import by.makhavenka.task.servlet.RequestController;
import by.makhavenka.task.exception.DAOException;
import by.makhavenka.task.exception.ServiceException;
import by.makhavenka.task.language.ConfigLocale;
import by.makhavenka.task.validator.*;

import java.util.ResourceBundle;

public class UserServiceImpl implements UserService {

    /**
     * method ups user rating
     * use  UserDaoImpl class, method:findById
     * @param content
     * @return String
     * @throws ServiceException
     */
    @Override
    public String upUserRating(RequestController content) throws ServiceException {
        int id= Integer.parseInt(content.getParameter(ServiceConstants.ID_USER));
        Dao dao = new UserDaoImpl();
        User user ;
        try {
            int rating;
            user = ((UserDaoImpl)dao).findById(id);
            rating = user.getRating();
            user.setRating(rating+25);
            ((UserDaoImpl) dao).update(user);
            content.setSessionAttribute(ServiceConstants.RATING_AUTHOR,user.getRating());
        } catch (DAOException e) {
            throw new ServiceException("up user rating service error",e);
        }
        return ServiceConstants.AUTHOR_PROFILE;
    }

    /**
     * method downs user rating
     * use  UserDaoImpl class, method:findById
     * @param content
     * @return String
     * @throws ServiceException
     */
    @Override
    public String downUserRating(RequestController content) throws ServiceException {
        int id= Integer.parseInt(content.getParameter(ServiceConstants.ID_USER));
        Dao dao = new UserDaoImpl();
        User user;
        try {
            int rating;
            user = ((UserDaoImpl)dao).findById(id);
            rating = user.getRating();
            user.setRating(rating-25);
            ((UserDaoImpl) dao).update(user);
            content.setSessionAttribute(ServiceConstants.RATING_AUTHOR,user.getRating());
        } catch (DAOException e) {
            throw new ServiceException("down user rating service error",e);
        }
        return ServiceConstants.AUTHOR_PROFILE;
    }

    /**
     * method edits user profile
     * use  UserDaoImpl class, method:findById
     * @param content
     * @return String
     * @throws ServiceException
     */
    @Override
    public String editProfile(RequestController content) throws ServiceException {
        boolean resultOfValidation = true;
        int userId =(int)content.getSessionAttribute(ServiceConstants.MAIN_ID);
        String editNick = content.getParameter(ServiceConstants.EDIT_NICKNAME_USER);
        if(!NicknameValidator.validate(editNick)){
            resultOfValidation=false;
        }
        String editName = content.getParameter(ServiceConstants.EDIT_NAME_USER);
        if(!NameUserValidator.validate(editName)){
            resultOfValidation=false;
        }
        String editSurname = content.getParameter(ServiceConstants.EDIT_SURNAME_USER);
        if(!SurnameValidator.validate(editSurname)){
            resultOfValidation=false;
        }
        String editMail = content.getParameter(ServiceConstants.EDIT_MAIL_USER);
        if(!EmailValidator.validate(editMail)){
            resultOfValidation=false;
        }
        String editPhone = content.getParameter(ServiceConstants.EDIT_PHONE_USER);
        if(!PhoneValidator.validate(editPhone)){
            resultOfValidation=false;
        }
        String editAge = content.getParameter(ServiceConstants.EDIT_AGE_USER);
        if(!AgeValidator.validate(editAge)){
            resultOfValidation=false;
        }
        String editSex = content.getParameter(ServiceConstants.EDIT_SEX_USER);
        if(!SexValidator.validate(editSex)){
            resultOfValidation=false;
        }
        String editFavoriteHero = content.getParameter(ServiceConstants.EDIT_FAVORITE_HERO);
        if(!FavoriteHeroValidator.validate(editFavoriteHero)){
            resultOfValidation=false;
        }

        if(!resultOfValidation){
            return ServiceConstants.HOME;
        }

        Dao dao = new UserDaoImpl();
        User user ;

        try {
            user = ((UserDaoImpl) dao).findById(userId);
            if(!editNick.isEmpty()){ user.setNickName(editNick);}
            if(!editName.isEmpty()){ user.setName(editName);}
            if(!editSurname.isEmpty()){ user.setSurname(editSurname);}
            if(!editMail.isEmpty()){ user.setMail(editMail);}
            if(!editPhone.isEmpty()){ user.setPhone(editPhone);}
            if(!editAge.isEmpty()){ user.setAge(Integer.parseInt(editAge));}
            if(!editSex.isEmpty()){ user.setSex(editSex);}
            if(!editFavoriteHero.isEmpty()){ user.setFavotiteHero(editFavoriteHero);}
            ((UserDaoImpl) dao).update(user);
        } catch (DAOException e) {
            throw new ServiceException("edit profile service error");
        }
        return ServiceConstants.HOME;
    }

    private void setLocaleConfig(RequestController content) {
        String locale=(String)content.getSessionAttribute(ServiceConstants.LANGUAGE);
        if(locale==null){
            content.setSessionAttribute(ServiceConstants.LANGUAGE,ServiceConstants.RU);
            ResourceBundle resourceBundle = ResourceBundle.getBundle(ServiceConstants.LOCALE);
            content.setSessionAttribute(ServiceConstants.ATTRIBUTE_NAME_FOR_LOGIN,resourceBundle.getString(ServiceConstants.LOGIN_ERROR));
        } else {
            ConfigLocale configLocale= new ConfigLocale((String)content.getSessionAttribute(ServiceConstants.LANGUAGE));
            content.setSessionAttribute(ServiceConstants.ATTRIBUTE_NAME_FOR_LOGIN,configLocale.getConfigLocale(ServiceConstants.LOGIN_ERROR));
        }
    }

    /**
     * method check in user
     * use  UserDaoImpl class, method: findLoginAndPassword
     * @param content
     * @return String
     * @throws ServiceException
     */
    @Override
    public String login(RequestController content) throws ServiceException {
        boolean resultOfValidation  = true;
        String login = content.getParameter(ServiceConstants.LOGIN_EMAIL);
        if(!LoginValidator.validate(login)){
            resultOfValidation= false;
        }
        String password = content.getParameter(ServiceConstants.LOGIN_PASSWORD);
        if(!PasswordValidator.validate(password)){
            resultOfValidation = false;
        }
        if(!resultOfValidation){
            return ServiceConstants.LOGIN_JSP;
        }
        UserDaoImpl dao = new UserDaoImpl();
        User dbUser;
        try {
            dbUser = dao.findLoginAndPassword(login,password);
            if(dbUser==null){
                setLocaleConfig(content);
                return ServiceConstants.LOGIN_JSP;}
            dbUser= dao.findByLogin(content.getParameter(ServiceConstants.LOGIN_EMAIL));
            content.setSessionAttribute(ServiceConstants.MAIN_ID,dbUser.getId());
            content.setSessionAttribute(ServiceConstants.USER_NICKNAME,dbUser.getNickName());
            if(dbUser.getRole().equalsIgnoreCase(ServiceConstants.USER)){
                content.setSessionAttribute(ServiceConstants.ROLE_STATUS,1);
            } else {content.setSessionAttribute(ServiceConstants.ROLE_STATUS,2);}
            return ServiceConstants.HOME;
        } catch (DAOException daoException) {
            throw new ServiceException("login service error",daoException);
        }

    }
}
