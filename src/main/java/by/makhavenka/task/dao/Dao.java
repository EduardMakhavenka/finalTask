package by.makhavenka.task.dao;

import by.makhavenka.task.entity.Entity;
import by.makhavenka.task.exception.DAOException;

import java.util.List;

public interface Dao<T extends Entity> {

    /**
     * find object by id
     * @param id
     * @return object T
     * @throws DAOException
     */
    T findById(int id) throws DAOException;

    /**
     * find all objects from database
     * @return List<T>
     * @throws DAOException
     */
    List<T> findAll() throws DAOException;

    /**
     * add entity to database
     * @param entity
     * @throws DAOException
     */
    void add(T entity) throws DAOException;

    /**
     * deleted object by id
     * @param id
     * @throws DAOException
     */
    void deleteById(int id) throws DAOException;
}
