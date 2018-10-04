package by.makhavenka.task.dao.impl;


import by.makhavenka.task.exception.DAOException;
import by.makhavenka.task.poolconnection.ConnectionProxy;
import by.makhavenka.task.poolconnection.Pool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ImageDaoImpl {

    public static final String ADD_IMAGE="INSERT into finaldb20.image (image,hero_id) values (?,?);";

    public void addImageHero(String name,int id) throws DAOException{
        try(ConnectionProxy connectionProxy = Pool.getInstance().takeConnection();
            PreparedStatement statement = connectionProxy.prepareStatement(ADD_IMAGE,Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
        }catch (SQLException e){
            throw  new DAOException("add image dao error",e);
        }
    }

}
