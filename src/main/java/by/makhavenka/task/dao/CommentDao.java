package by.makhavenka.task.dao;

import by.makhavenka.task.entity.Comment;
import by.makhavenka.task.exception.DAOException;

import java.util.Map;

public interface CommentDao extends Dao {

    /**
     * Find a comment on the user’s hero
     * @param idUser
     * @return Map<String,String>
     * @throws DAOException
     */
    Map<String,String> findAllById(int idUser)throws DAOException;

    /**
     * find comment by comments id
     * @param id
     * @return Comment
     * @throws DAOException
     */
    Comment findCommentById(int id) throws DAOException;

}
