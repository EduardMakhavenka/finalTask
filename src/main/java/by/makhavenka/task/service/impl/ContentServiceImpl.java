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
import by.makhavenka.task.service.ContentService;
import by.makhavenka.task.servlet.RequestController;
import by.makhavenka.task.validator.CommentValidator;
import by.makhavenka.task.validator.SearchValidator;

import java.util.*;

public class ContentServiceImpl implements ContentService {
    @Override
    public String addComment(RequestController content) throws ServiceException {
        int id =(int)content.getSessionAttribute(ServiceConstants.MAIN_ID);
        if(!CommentValidator.validate(content.getParameter(ServiceConstants.COMMENT))){
         return ServiceConstants.DESCRIPTION;
        }
        Comment comment = new Comment();
        comment.setContent(content.getParameter(ServiceConstants.COMMENT));
        comment.setHeroId(Integer.parseInt(content.getParameter(ServiceConstants.ID_HERO)));
        comment.setUserId(id);
        comment.setDeleted(0);
        Dao dao = new CommentDaoImpl();
        Dao heroDao = new HeroDaoImpl();

        Map<Hero,ArrayList<Comment>> daoInfo;
        Set<Map.Entry<Hero,ArrayList<Comment>>> entrySet;
        ArrayList<Comment> comments = null;
        try {
            dao.add(comment);
            daoInfo=((HeroDaoImpl) heroDao).findAllCommentsByHeroId(comment.getHeroId());
            entrySet=daoInfo.entrySet();
            for(Map.Entry<Hero,ArrayList<Comment>> entry:entrySet){
                comments = entry.getValue();
            }
            content.setSessionAttribute(ServiceConstants.COMMENTS_HEROES_LIST,comments);
        } catch (DAOException e) {
            throw  new ServiceException("addComment error",e);
        }
     return ServiceConstants.DESCRIPTION;
    }

    @Override
    public String downHeroRating(RequestController content) throws ServiceException {
        int heroId = Integer.parseInt(content.getParameter(ServiceConstants.ID_HERO));
        Dao dao = new HeroDaoImpl();
        Hero hero;
        try {
            hero= ((HeroDaoImpl) dao).findById(heroId);
            int rating= hero.getRating();
            hero.setRating(rating-25);
            ((HeroDaoImpl) dao).update(hero);
            content.setSessionAttribute(ServiceConstants.RATING,hero.getRating());
        } catch (DAOException e) {
            throw new ServiceException("downHerorating service error",e);
        }
        return ServiceConstants.DESCRIPTION;
    }

    @Override
    public String showDescriptionHero(RequestController content) throws ServiceException {

        String id = content.getParameter(ServiceConstants.ID);
        int heroId = Integer.parseInt(id);
        Dao heroDao = new HeroDaoImpl();
        Map<Hero,ArrayList<Comment>> daoInfo;
        Hero hero;
        Set<Map.Entry<Hero,ArrayList<Comment>>> entrySet;
        ArrayList<Comment> comments = null;
        String image;

        try {
            daoInfo=((HeroDaoImpl) heroDao).findAllCommentsByHeroId(heroId);
            entrySet=daoInfo.entrySet();
            image = ((HeroDaoImpl) heroDao).findImage(heroId);
            for(Map.Entry<Hero,ArrayList<Comment>> entry:entrySet){
                comments = entry.getValue();
            }

            hero = ((HeroDaoImpl) heroDao).findById(heroId);

            content.setSessionAttribute(ServiceConstants.HERO_ID,hero.getId());
            content.setSessionAttribute(ServiceConstants.NAME,hero.getName());
            content.setSessionAttribute(ServiceConstants.IMAGE,image);
            content.setSessionAttribute(ServiceConstants.RATING,hero.getRating());
            content.setSessionAttribute(ServiceConstants.HERO_TYPE,hero.getType());
            content.setSessionAttribute(ServiceConstants.DESCRIPTION_HERO,hero.getDescription());
            content.setSessionAttribute(ServiceConstants.COMMENTS_HEROES_LIST,comments);

            content.setSessionAttribute("FilterAttribute",1);
        } catch (DAOException e) {
            throw new ServiceException(" showDescription service error",e);
        }
        return ServiceConstants.DESCRIPTION;
    }

    @Override
    public String showMKXContent(RequestController content) throws ServiceException {
        Dao dao = new HeroDaoImpl();
        List<Hero> allHeroes;
        List<Hero> heroes = new ArrayList<>();

        try {
            allHeroes = dao.findAll();
            for(Hero hero:allHeroes){
                if(hero.getDeleted()==0 && hero.getType().equals(ServiceConstants.MKX)){
                    heroes.add(hero);
                }
            }
            content.setSessionAttribute(ServiceConstants.HEROES,heroes);
        } catch (DAOException e) {
            throw  new ServiceException("show mkx service error",e);
        }
        return ServiceConstants.MKX_JSP;
    }

    @Override
    public String showMarvelContent(RequestController content) throws ServiceException {
        Dao dao = new HeroDaoImpl();
        List<Hero> allHeroes;
        List<Hero> heroes = new ArrayList<>();

        try {
            allHeroes = dao.findAll();
            for(Hero hero:allHeroes){
                if(hero.getDeleted()==0 && hero.getType().equals(ServiceConstants.MARVEL)){
                    heroes.add(hero);
                }
            }
            content.setSessionAttribute(ServiceConstants.HEROES,heroes);
        } catch (DAOException e) {
            throw  new ServiceException("show marvel service error",e);
        }
        return ServiceConstants.MARVEL_JSP;
    }

    @Override
    public String showRickAndMortyContent(RequestController content) throws ServiceException {
        Dao dao = new HeroDaoImpl();
        List<Hero> allHeroes;
        List<Hero> heroes = new ArrayList<>();

        try {
            allHeroes = dao.findAll();
            for(Hero hero:allHeroes){
                if(hero.getDeleted()==0 && hero.getType().equals(ServiceConstants.RICK)){
                    heroes.add(hero);
                }
            }
            content.setSessionAttribute(ServiceConstants.HEROES,heroes);
        } catch (DAOException e) {
            throw  new ServiceException("show rick service error",e);
        }
        return ServiceConstants.RICK_JSP;
    }

    @Override
    public String upHeroRating(RequestController content) throws ServiceException {
        int heroId = Integer.parseInt(content.getParameter(ServiceConstants.ID_HERO));
        Dao dao = new HeroDaoImpl();
        Hero hero ;
        try{
            hero= ((HeroDaoImpl) dao).findById(heroId);
            int rating= hero.getRating();
            hero.setRating(rating+25);
            ((HeroDaoImpl) dao).update(hero);
            content.setSessionAttribute(ServiceConstants.RATING,hero.getRating());
        } catch (DAOException e) {
            throw new ServiceException("upHerorating service error",e);
        }
        return ServiceConstants.DESCRIPTION;
    }

    @Override
    public String searchHero(RequestController content) throws ServiceException {
        String heroName = content.getParameter(ServiceConstants.SEARCH_HERO);
        if(!SearchValidator.validate(heroName)){
            return ServiceConstants.HOME;
        }
        Dao heroDao = new HeroDaoImpl();
        List<Hero> heroes;

        try {
            heroes = ((HeroDaoImpl) heroDao).searchByName(heroName);
            content.setSessionAttribute(ServiceConstants.SEARCH_HERO_LIST,heroes);
        } catch (DAOException e) {
            throw  new ServiceException("search service error",e);
        }
        return ServiceConstants.SEARCH_PAGE;
    }

    @Override
    public String showAuthorProfile(RequestController content) throws ServiceException {
        int commentId = Integer.parseInt(content.getParameter(ServiceConstants.ID_COMMENT));
        CommentDaoImpl commentDao = new CommentDaoImpl();
        Dao userDao = new UserDaoImpl();
        Comment comment;
        User user;

        try {
            comment=commentDao.findCommentById(commentId);
            user= ((UserDaoImpl) userDao).findById(comment.getUserId());

            content.setSessionAttribute(ServiceConstants.USER_ID,user.getId());
            content.setSessionAttribute(ServiceConstants.NICKNAME_AUTHOR,user.getNickName());
            content.setSessionAttribute(ServiceConstants.NAME_AUTHOR,user.getName());
            content.setSessionAttribute(ServiceConstants.SURNAME_AUTHOR,user.getSurname());
            content.setSessionAttribute(ServiceConstants.MAIL_AUTHOR,user.getMail());
            content.setSessionAttribute(ServiceConstants.PHONE_AUTHOR,user.getPhone());
            content.setSessionAttribute(ServiceConstants.AGE_AUTHOR,user.getAge());
            content.setSessionAttribute(ServiceConstants.ROLE_AUTHOR,user.getRole());
            content.setSessionAttribute(ServiceConstants.SEX_AUTHOR,user.getSex());
            content.setSessionAttribute(ServiceConstants.RATING_AUTHOR,user.getRating());
            content.setSessionAttribute(ServiceConstants.FAVORITEHERO_AUTHOR,user.getFavotiteHero());
            content.setSessionAttribute(ServiceConstants.PHOTO_AUTHOR,user.getPhoto());

            content.setSessionAttribute("FilterAttributeProfile",1);
        } catch (DAOException e) {
            throw new ServiceException("show author profile service error",e);
        }

        return ServiceConstants.AUTHOR_PROFILE;
    }
}
