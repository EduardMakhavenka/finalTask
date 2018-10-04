package by.makhavenka.task.dao;

import by.makhavenka.task.entity.User;
import by.makhavenka.task.exception.DAOException;


public interface UserDao extends Dao {

    User findByLogin(String login) throws DAOException;
    User findLoginAndPassword(String login,String password) throws DAOException;
    void update(User user) throws DAOException;
    void restoreById(int id) throws DAOException;
}
