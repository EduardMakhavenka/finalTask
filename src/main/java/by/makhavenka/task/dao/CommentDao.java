package by.makhavenka.task.dao;

import by.makhavenka.task.entity.Comment;
import by.makhavenka.task.exception.DAOException;

import java.util.Map;

public interface CommentDao extends Dao {

    Map<String,String> findAllById(int idUser)throws DAOException;
    Comment findCommentById(int id) throws DAOException;

}
