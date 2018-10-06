package by.makhavenka.task.dao.impl;


import by.makhavenka.task.dao.UserDao;
import by.makhavenka.task.dao.builder.UserBuilder;
import by.makhavenka.task.dao.DaoConstant;
import by.makhavenka.task.entity.Entity;
import by.makhavenka.task.entity.User;
import by.makhavenka.task.exception.DAOException;
import by.makhavenka.task.poolconnection.ConnectionProxy;
import by.makhavenka.task.poolconnection.Pool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {


    private final static String SELECT_ALL = "SELECT id,name,surname,login,mail,password,rating," +
            "banned,role,photo,phone,sex,age,favoritehero,nickname FROM finaldb20.user";

    private final static String SELECT_LOGIN_AND_PASSWORD = "SELECT id,login,password FROM finaldb20.user " +
            "WHERE user.login = (?) and user.password=md5(?) and user.banned = 0";

    private final static String ADD_USER="INSERT INTO finaldb20.user (name,surname,login,mail,password," +
            " rating,banned,role,photo,phone,sex,age,favoritehero,nickname )" +
            " VALUES (?,?,?,?,md5(?),?,?,?,?,?,?,?,?,?)";

    private final static String FIND_BY_LOGIN = "SELECT id,name,surname,login,mail,password," +
            " rating,banned,role,photo,phone,sex,age,favoritehero,nickname " +
            "FROM finaldb20.user WHERE login = (?)";

    private final  static String DELETE_BY_ID ="UPDATE finaldb20.user SET banned =? WHERE id = ?";

    private final static String UPDATE ="UPDATE finaldb20.user SET  name = ?,surname = ?,login = ?," +
            "mail = ?,password = ?,rating = ?,banned = ?,role = ?,photo =?,phone =?,sex =?,age= ?," +
            "favoritehero=?,nickname =? WHERE id = ?";

    private final static String FIND_BY_ID = "SELECT id,name,surname,login,mail,password," +
            "rating,banned,role,photo,phone,sex,age,favoritehero,nickname FROM finaldb20.user " +
            "WHERE id = ? ";

    /**
     * update user in database
     * @param user
     * @throws DAOException
     */
    public void update(User user) throws DAOException{
        try(ConnectionProxy connectionProxy = Pool.getInstance().takeConnection();
            PreparedStatement statement = connectionProxy.prepareStatement(UPDATE)){
            UserBuilder builder = new UserBuilder();
            statement.setInt(15,user.getId());
            (builder.fillStatement(user,statement)).executeUpdate();

        }catch (SQLException e){
            throw new DAOException("user dao 'update' error",e);
        }
    }

    /**
     * return user by login and password
     * @param login
     * @param password
     * @return user
     * @throws DAOException
     */
    public User findLoginAndPassword(String login,String password) throws DAOException {
        User user = null;
        try(ConnectionProxy connectionProxy = Pool.getInstance().takeConnection();
            PreparedStatement statement = connectionProxy.prepareStatement(SELECT_LOGIN_AND_PASSWORD)){
            statement.setString(1,login);
            statement.setString(2,password);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                user = new User();
                user.setId(set.getInt(DaoConstant.ID));
                user.setLogin(set.getString(DaoConstant.LOGIN));
                user.setPassword(set.getString(DaoConstant.PASSWORD));
            }
        }catch (SQLException e){
            throw new DAOException("user dao 'findloginandpassword' error",e);
        }
        return user;
    }

    /**
     * find user by login and return
     * @param login
     * @return user
     * @throws DAOException
     */
    public User findByLogin(String login) throws DAOException {
        User user = null;
        try(ConnectionProxy connectionProxy = Pool.getInstance().takeConnection();
            PreparedStatement statement = connectionProxy.prepareStatement(FIND_BY_LOGIN)){
            statement.setString(1,login);
            ResultSet set = statement.executeQuery();
            UserBuilder builder = new UserBuilder();
            while (set.next()){
            user = builder.build(set);
            }
        }catch (SQLException e){
            throw new DAOException("user dao 'findbylogin' error",e);
        }
        return user;
    }

    /**
     * find user by id and return
     * @param id
     * @return object user
     * @throws DAOException
     */
    @Override
    public User findById(int id) throws DAOException {
        User user = null;
        try(ConnectionProxy connectionProxy = Pool.getInstance().takeConnection();
            PreparedStatement statement = connectionProxy.prepareStatement(FIND_BY_ID)){
            statement.setInt(1,id);
            ResultSet set = statement.executeQuery();
            UserBuilder builder = new UserBuilder();
            while (set.next()) {
                user = builder.build(set);
            }
        }catch (SQLException e){
            throw new DAOException("user dao 'findbyid' error",e);
        }
        return user;
    }

    /**
     * find all users from database
     * @return  List<User>
     * @throws DAOException
     */
    @Override
    public List findAll() throws DAOException {
        User user ;
        List<User> users = new ArrayList<>();
        try(ConnectionProxy connectionProxy = Pool.getInstance().takeConnection();
            PreparedStatement statement = connectionProxy.prepareStatement(SELECT_ALL)){
            ResultSet set = statement.executeQuery();
            UserBuilder builder = new UserBuilder();
            while (set.next()) {
                user = builder.build(set);
                users.add(user);
            }
        }catch (SQLException e){
            throw new DAOException("user dao 'findall' error",e);
        }
        return users;
    }

    /**
     * add user to database
     * @param entity
     * @throws DAOException
     */
    @Override
    public void add(Entity entity) throws DAOException {
        User user = (User)entity;
        try(ConnectionProxy connectionProxy = Pool.getInstance().takeConnection();
            PreparedStatement statement = connectionProxy.prepareStatement(ADD_USER,Statement.RETURN_GENERATED_KEYS)) {
            UserBuilder builder = new UserBuilder();
            (builder.fillStatement(user,statement)).executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            user.setId(resultSet.getInt(1));
        }catch (SQLException e)
        {
            switch (e.getErrorCode()){
                case 1406: throw new DAOException("Login must be more than 80 characters",e);
                case 1062: throw new DAOException(String.format("Client with login '%s' already exists", user.getLogin()),e);
                default:   throw new DAOException("user dao 'add' error",e);
            }
        }
    }


    /**
     * marks a user as deleted by id in database
     * @param id
     * @throws DAOException
     */
    @Override
    public void deleteById(int id) throws DAOException {
        try(ConnectionProxy connectionProxy = Pool.getInstance().takeConnection();
            PreparedStatement statement = connectionProxy.prepareStatement(DELETE_BY_ID)){
            statement.setInt(1,1);
            statement.setInt(2,id);
            statement.executeUpdate();
        }catch (SQLException e){
            throw new DAOException("user dao 'deletebyid' error",e);
        }
    }

    /**
     * restore user by user id
     * @param id
     * @throws DAOException
     */
    public void restoreById(int id) throws DAOException {
        try (ConnectionProxy connectionProxy = Pool.getInstance().takeConnection();
            PreparedStatement statement = connectionProxy.prepareStatement(DELETE_BY_ID)) {
            statement.setInt(1, 0);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("user dao 'restorebyid' error", e);
        }
    }
}
