package by.makhavenka.task.dao;

import by.makhavenka.task.entity.Comment;
import by.makhavenka.task.entity.Hero;
import by.makhavenka.task.exception.DAOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface HeroDao extends Dao{

    Map<Hero,ArrayList<Comment>> findAllCommentsByHeroId(int id) throws DAOException;
    String findImage(int id) throws DAOException;
    List<Hero> searchByName(String name) throws DAOException;
    void restoreById(int id) throws DAOException;
    List<Hero> findAllDeleted() throws DAOException;
    void update(Hero hero) throws DAOException;

}
