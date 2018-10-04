package by.makhavenka.task.dao;

import by.makhavenka.task.entity.Entity;
import by.makhavenka.task.exception.DAOException;

import java.util.List;

public interface Dao<T extends Entity> {

    T findById(int id) throws DAOException;

    List<T> findAll() throws DAOException;

    void add(T entity) throws DAOException;

    void deleteById(int id) throws DAOException;
}
