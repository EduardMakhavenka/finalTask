package by.makhavenka.task.service.impl;

import by.makhavenka.task.service.ServiceConstants;
import by.makhavenka.task.dao.Dao;
import by.makhavenka.task.dao.impl.CommentDaoImpl;
import by.makhavenka.task.dao.impl.HeroDaoImpl;
import by.makhavenka.task.dao.impl.UserDaoImpl;
import by.makhavenka.task.entity.Comment;
import by.makhavenka.task.entity.Hero;
import by.makhavenka.task.entity.User;
import by.makhavenka.task.exception.DAOException;
import by.makhavenka.task.exception.ServiceException;
import by.makhavenka.task.service.AdminService;
import by.makhavenka.task.servlet.RequestController;
import by.makhavenka.task.validator.DescriptionValidator;
import by.makhavenka.task.validator.NameHeroValidator;
import by.makhavenka.task.validator.RatingValidator;
import by.makhavenka.task.validator.TypeValidator;

import java.util.*;

public class AdminServiceImpl implements AdminService {
    @Override
    public String blockAccount(RequestController content) throws ServiceException {
        int id= Integer.parseInt(content.getParameter(ServiceConstants.ID_USER));
        Dao dao = new UserDaoImpl();
        try {
            dao.deleteById(id);
        } catch (DAOException e) {
            throw new ServiceException("admin service 'blockAccount' error",e);
        }
        return ServiceConstants.HOME;
    }

    @Override
    public String deleteComment(RequestController content) throws ServiceException {
        int commentId = Integer.parseInt(content.getParameter(ServiceConstants.ID_COMMENT));
        int heroId = Integer.parseInt(content.getParameter(ServiceConstants.ID_HERO));
        Dao dao = new CommentDaoImpl();
        Dao heroDao = new HeroDaoImpl();
        Map<Hero,ArrayList<Comment>> daoInfo = new HashMap<>();
        Set<Map.Entry<Hero,ArrayList<Comment>>> entrySet;
        ArrayList<Comment> comments = null;
        try {
            dao.deleteById(commentId);

            daoInfo=((HeroDaoImpl) heroDao).findAllCommentsByHeroId(heroId);
            entrySet=daoInfo.entrySet();
            for(Map.Entry<Hero,ArrayList<Comment>> entry:entrySet){
                comments = entry.getValue();
            }
            content.setSessionAttribute(ServiceConstants.COMMENTS_HEROES_LIST,comments);
        } catch (DAOException e) {
            throw new ServiceException("deletecomment service error",e);
        }
        return ServiceConstants.DESCRIPTION;
    }


    @Override
    public String unBlockAccount(RequestController content) throws ServiceException {
        int id= Integer.parseInt(content.getParameter(ServiceConstants.ID_BANS_USER));
        Dao dao = new UserDaoImpl();
        try {
            ((UserDaoImpl) dao).restoreById(id);
        } catch (DAOException e) {
            throw new ServiceException("admin service 'unblockAccount' error",e);
        }

        return ServiceConstants.HOME;
    }


    @Override
    public String deleteHero(RequestController content) throws ServiceException {
        int heroId =  Integer.parseInt(content.getParameter(ServiceConstants.ID_HERO));
        Dao heroDao = new HeroDaoImpl();
        try {
            heroDao.deleteById(heroId);
        } catch (DAOException e) {
            throw new ServiceException("delete hero service error",e);
        }
        return ServiceConstants.HOME;
    }

    @Override
    public String editHero(RequestController content) throws ServiceException {
        boolean resultOfValidation=true;
        int heroId =Integer.parseInt(content.getParameter(ServiceConstants.ID_HERO));
        String editName= content.getParameter(ServiceConstants.EDIT_NAME_HERO);
        if(!NameHeroValidator.validate(editName)){
            resultOfValidation = false;
        }
        String editRating =content.getParameter(ServiceConstants.EDIT_RATING_HERO);
        if(!RatingValidator.validate(editRating)){
            resultOfValidation = false;
        }
        String editType = content.getParameter(ServiceConstants.EDIT_TYPE_HERO);
        if(!TypeValidator.validate(editType)){
            resultOfValidation = false;
        }
        String editDescription = content.getParameter(ServiceConstants.EDIT_DESCRIPTION_HERO);
        if(!DescriptionValidator.validate(editDescription)){
            resultOfValidation = false;
        }
        System.out.println(resultOfValidation);
        if(!resultOfValidation){
            return ServiceConstants.HOME;
        }

        Hero hero ;
        Dao daoHero = new HeroDaoImpl();

        try {
            hero= ((HeroDaoImpl) daoHero).findById(heroId);
            if(!editName.isEmpty()){ hero.setName(editName);}
            if(!editRating.isEmpty()){ hero.setRating(Integer.parseInt(editRating));}
            if(!editType.isEmpty()){ hero.setType(editType);}
            if(!editDescription.isEmpty()){ hero.setDescription(editDescription);}
            ((HeroDaoImpl) daoHero).update(hero);

        } catch (DAOException e) {
            throw  new ServiceException("edit hero service error",e);
        }
        return ServiceConstants.HOME;
    }

    @Override
    public String addHero(RequestController content) throws ServiceException {
        boolean resultOfValidation=true;
        String name = content.getParameter(ServiceConstants.ADD_NAME_HERO);
        if(!NameHeroValidator.validate(name)){
            resultOfValidation = false;
        }
        String rating = content.getParameter(ServiceConstants.ADD_RATING_HERO);
        if(!RatingValidator.validate(rating)){
            resultOfValidation = false;
        }
        String type = content.getParameter(ServiceConstants.ADD_TYPE_HERO);
        if(!TypeValidator.validate(type)){
            resultOfValidation = false;
        }
        String description = content.getParameter(ServiceConstants.ADD_DESCRIPTION_HERO);
        if(!DescriptionValidator.validate(description)){
            resultOfValidation = false;
        }
        if(!resultOfValidation){
            return ServiceConstants.ADMIN_PAGE;
        }

        Hero hero = new Hero();
        hero.setName(name);
        hero.setRating(Integer.parseInt(rating));
        hero.setType(type);
        hero.setDescription(description);
        Dao dao = new HeroDaoImpl();

        try {
            dao.add(hero);
        } catch (DAOException e) {
            throw new ServiceException("add hero service error",e);
        }
        return ServiceConstants.ADMIN_PAGE;
    }

    @Override
    public String showBannedUsers(RequestController content) throws ServiceException {
        Dao dao = new UserDaoImpl();
        List<User> users;
        List<User> bannedUsers = new ArrayList<>();
        try {
            users = dao.findAll();
            for(User user:users){
                if(user.getBanned()==1){
                    bannedUsers.add(user);
                }
            }
            content.setSessionAttribute(ServiceConstants.BANNED_USERS_LIST,bannedUsers);
        } catch (DAOException e) {
            throw  new ServiceException("show banned user service error",e);
        }
        return ServiceConstants.BANNED_LIST;
    }

    @Override
    public String showDeletedComments(RequestController content) throws ServiceException {
        Dao dao = new CommentDaoImpl();

        List<Comment> comments;
        List<Comment> deletedComments = new ArrayList<>();

        try {
            comments = dao.findAll();
            for(Comment comment:comments){
                if(comment.getDeleted()==1){
                    deletedComments.add(comment);
                }
            }
            content.setSessionAttribute(ServiceConstants.DELETED_COMMENTS_LIST,deletedComments);
        } catch (DAOException e) {
            throw new ServiceException("show deleted comments service error",e);
        }
        return ServiceConstants.DELETED_COMMENTS;
    }

    @Override
    public String showDeletedHeroes(RequestController content) throws ServiceException {
        Dao dao = new HeroDaoImpl();
        List<Hero> deletedHeroes;
        try {
            deletedHeroes = ((HeroDaoImpl) dao).findAllDeleted();
            content.setSessionAttribute(ServiceConstants.DELETED_HEROES_LIST,deletedHeroes);
        } catch (DAOException e) {
            throw  new ServiceException("show deleted heroes service error",e);
        }
        return ServiceConstants.DELETED_HEROES;
    }

    @Override
    public String showUsers(RequestController content) throws ServiceException {
        Dao dao = new UserDaoImpl();
        List<User> allUsers;
        List<User> users = new ArrayList<>();
        try {
            allUsers = dao.findAll();
            for (User user:allUsers){
                if(user.getBanned()==0){
                    users.add(user);
                }
            }
            content.setSessionAttribute(ServiceConstants.ACTIVE_USERS_LIST,users);
        } catch (DAOException e) {
            throw new ServiceException("show users service error",e);
        }
        return ServiceConstants.USER_LIST;
    }

    @Override
    public String restoreHero(RequestController content) throws ServiceException {
        int heroId = Integer.parseInt(content.getParameter(ServiceConstants.ID_DELETED_HERO));
        Dao dao = new HeroDaoImpl();
        try {
            ((HeroDaoImpl) dao).restoreById(heroId);
        } catch (DAOException e) {
            throw new ServiceException("restore hero service error",e);
        }
        return ServiceConstants.HOME;
    }
}

